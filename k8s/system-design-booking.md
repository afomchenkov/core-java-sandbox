## Appointments booking

```
Redesign the Calendar functionality, to build a distributed, scalable system that
allows patients to search for and book appointments with practitioners. The system
should handle searches based on location, specialty, and availability, as well as
support booking, rescheduling, and cancelling appointments.

The system needs to support 50 million patients, 500K practitioners, and handle 700
searches and 90 bookings per second. To meet our expansion goals, the system
needs to make launching a new country easy.

Consider technical solutions for scaling, fault tolerance, and data consistency
across multiple service instances.
```

- Scalability: Handle 700 searches and 90 bookings per second.
- Fault Tolerance: Ensure high availability despite failures.
- Data Consistency: Maintain accurate appointment data across multiple services.
- Global Expansion: Easily launch in new regions or countries.



Search	700 requests/second
Booking	90 requests/second
Latency (Search)	< 200ms
Fault Tolerance	< 1% downtime


## ----



> High load (mid siaze apps):
- Average RPS: Around 100 to 1,000 RPS

> Very high load (large scale systems):
- Average RPS: 1,000 to 100,000+ RPS

> Examples by industry:
- Websites: 100-10,000 RPS for typical high traffic.
- APIs: 1,000-50,000 RPS for enterprise APIs.
- Streaming Services: 10,000-100,000 RPS for global services during peak times.
- Gaming Servers: 10,000-1,000,000 RPS for massive multiplayer online games.




## Questions
- What amount of scalability to expect? Handle 1000 searches and 300 bookings per second or more?
- Which security/auth flow to follow, JWT token, third party Auth tools AWS Cognito/Auth0 etc.?
- Do we expect the reduced amount of traffic let's say at night or on weekend days?
- Should we have the notification system/service to inform the practitioners/patients over SMS/Email?
- Which tools to use for MS/Async communication, hosting MS etc.: Kafka, AWS, K8S...?
- Which DB to use, PostgreSQL + ES?
- Which availability zones to support now (one or a few like America, Europe, etc.)?
- Ho we should handle availability zones, subdomains or load balancing based on the query params etc.:
    - https://europe.doctolib.com/search?query=something
    - https://america.doctolib.com/search?query=something
    - https://asia.doctolib.com/search?query=something





## Solution

> Core services
- Search Service: Handles patient searches for practitioners by location, specialty, and availability.
- Booking Service: Manages appointment creation, rescheduling, and cancellations.
- Practitioner Management Service: Stores and updates practitioner profiles and availability.
- Patient Management Service: Manages patient information and preferences.
- Notification Service: Sends reminders and confirmations for appointments via email/SMS.
Monitoring and Logging Service: Tracks system health, performance, and errors.


> Data storage
- Search Index: Use Elasticsearch for fast and flexible searches.
- Relational Database: Use PostgreSQL for strong consistency and transactions for booking data.
- Cache: Use Redis for frequently accessed data (e.g., practitioner availability).

> Message Queue
- Use Kafka or RabbitMQ for asynchronous communication and event-driven architecture.

# ------------------------------------------------------------------------

# ---- Search service ----

## Work flow

1. Patients search by location, specialty, and availability.
2. Search results are fetched from an Elasticsearch index.
    > sharding for scaling ES: Use sharding for Elasticsearch (e.g., by country or region) to handle a high volume of searches.



## Data flow
1. Practitioner data (e.g., specialty, location, availability) is ingested into Elasticsearch from the Practitioner Management Service.
2. Sync data periodically using change data capture (CDC) mechanisms.


## Example query
```
{
  "query": {
    "bool": {
      "must": [
        { "match": { "location": "San Francisco" }},
        { "match": { "specialty": "Dermatologist" }},
        { "range": { "availability": { "gte": "2024-11-19T09:00:00" }}}
      ]
    }
  }
}
```


# ---- Booking service ----

## Work flow
1. Patients book, reschedule, or cancel appointments.
2. Bookings involve strong consistency to prevent double booking.

## Appointments table
```
appointment_id (Primary Key)
patient_id
practitioner_id
start_time
end_time
status (e.g., booked, canceled)
```

## Data consistency
Use distributed transactions or a lock manager (e.g., Redlock with Redis)
to ensure no overlapping appointments.

## Scalability
Partition the database by practitioner ID or region to distribute the load.


# ---- Practitioner service ----

## Workflow
1. Practitioners update their profile, availability, and location.
2. Changes propagate to the Search Service and Booking Service.

## Practitioner table
```
practitioner_id (Primary Key)
name
specialty
location
availability (JSON or timeslot-based schema)
```

## Scaling
Use database replicas for read-heavy operations like availability queries.




## Global expansion
> Regional sharding
1. Deploy regional clusters for data residency and low latency.
2. Use a global load balancer (e.g., AWS Route 53 or Cloudflare) to direct traffic to the nearest region.

## Data federation
> Use tools like AWS Aurora Global Database or CockroachDB for cross-region database synchronization.




## Scaling and fault tolerance
- stateless microservices (to allow horizontal scaling)
- use K8S for auto-scaling pods based on CPU/memory usage or custom metrics e.g. request rate
- use Redis to cache:
    - practitioner availability data for quick access during searches
    - recent searches for faster response time
- user circuit breaker pattern to gracefully handle downstream service failures
> Failover
- use multi-AZ deployments for DB and brokers
- configure load balancer ro reroute traffic during service failures



## Data consistency
a) Booking Consistency
    Use transactional locks to prevent race conditions during appointment booking.
    Example with Redis:
        Acquire a lock on a time slot (practitioner_id + time_range).
        Proceed with booking if lock is acquired; otherwise, retry or return an error.

b) Event-Driven Updates
    Use an event bus (e.g., Kafka) to propagate updates between services.
        Example: Booking Service publishes an "appointment booked" event, which the Practitioner Management Service consumes to update availability.

c) Conflict Resolution
    For eventual consistency (e.g., between search index and booking database), use a last-write-wins approach or reprocess events to resolve conflicts.






# ------------------------------------------------------------------------

## Integration of PostgreSQL and Elasticsearch

1. Master PostgreSQL handles write operations
2. Replica handles read-heavy operations for reports or backups but not Elasticsearch updates (to avoid lag-induced inconsistency).
3. Serves as a secondary index for fast lookup, optimized for search and filtering on appointment slots.

> Synchronization Layer: Keeps Elasticsearch in sync with PostgreSQL in near-real-time or on a schedule.


## Data modelling:

> Index Name: free_slots
> Document Fields:
- slot_id: Unique identifier for the slot.
- appointment_date: Date of the slot.
- start_time / end_time: Slot timings.
- is_free: Boolean or status indicator.
- Other metadata (e.g., doctor_id, location_id).

## Change data capture (CDC) in Postgres
> To ensure consistency between PostgreSQL and Elasticsearch:

- Logical Replication or WAL (Write-Ahead Logging):
Use tools like Debezium or built-in PostgreSQL logical replication to capture changes (inserts, updates, deletes).
Send these changes to a message queue (e.g., Kafka, RabbitMQ) or directly to Elasticsearch.
- Trigger-Based Approach (alternative):
Use PostgreSQL triggers to capture changes in relevant tables (e.g., appointments) and push updates to Elasticsearch.

## Data synchronization
- Insert, update, or delete documents in Elasticsearch based on changes in PostgreSQL.

Tools:
- Custom scripts using libraries like Python's elasticsearch-py.
- Change propagators like Kafka Connect, Logstash, or Fluentd.
Workflow Example:
- New appointment booked → mark slot as is_free=false in PostgreSQL → send update to Elasticsearch.
- Appointment canceled → mark slot as is_free=true → update Elasticsearch.

## Query optimization in ES
> Use time-based queries and filters for efficient search:
- Example: Search for free slots on a specific date, within a time range, or for a specific doctor.
- Define custom analyzers for text search if slot descriptions involve specific terms.

## Consistency check
> Schedule a periodic full sync:
- Compare PostgreSQL free slots with Elasticsearch index.
- Resolve discrepancies to ensure data consistency.
- Run consistency checks during off-peak hours.


## Distributed transactions in PostgreSQL

Distributed transactions in PostgreSQL involve coordinating changes across multiple databases or systems,
ensuring that all operations either succeed or fail as a single unit of work. PostgreSQL supports 
distributed transactions primarily through two mechanisms:
- Two-Phase Commit (2PC) and Logical Replication.
- For more advanced distributed setups, external tools like PostgreSQL foreign data wrappers 
(FDWs) or third-party frameworks can be used.

## Use cases for distributed transactions
1. Coordinating updates across multiple PostgreSQL instances.
2. Integrating PostgreSQL with other databases or services while ensuring atomicity.
3. Microservices requiring consistent state management across independent services.

## When to Use 2PC
- Multi-database PostgreSQL setups (e.g., for partitioned data).
- Scenarios where atomicity is critical across distributed nodes.


## ----- PostgreSQL sharding/partitioning -----
    Problem	                            Sharding	                         Partitioning
Scalability	                Horizontal scaling via shards	        Partition pruning for faster queries
Performance Bottlenecks	    Distributes load across nodes	        Limits data scanned per query
Geographic Latency	        Data closer to users	                 N/A
Storage Limitations	        Aggregate capacity of shards	        Archiving old partitions
Fault Tolerance	            Failover per shard	                    Partition-specific recovery
Maintenance Overheads	    Isolates maintenance to shards	        Per-partition maintenance
Query Contention	        Reduces contention per shard	        Localized partition locking
Multi-Tenancy	            Tenant-specific shards	                Tenant-specific partitions
Compliance	                Geographic sharding	                    Region-specific partitions
Legacy Data Management	    Historical data in separate shards	    Historical partitions
Complex Query Management	Isolates queries to relevant shards	    Query pruning for partitions
