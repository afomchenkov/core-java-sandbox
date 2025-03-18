package Time;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
// import java.nio.charset.StandardCharsets;
// import java.util.concurrent.ExecutionException;
// import java.util.concurrent.Future;

public class JavaTimeFormatting {
    static class MetricsException extends RuntimeException {
        public MetricsException(String message, Throwable err) {
            super(message, err);
        }
    }

    static class DTExample implements Closeable {
        public static final String BROKER_LIST = "broker_list";
        public static final String TOPIC = "topic";

        private String hostname = null;
        private String brokerList = null;
        private String topic = null;

        private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        private final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        private final ZoneId zoneId = ZoneId.systemDefault();

        void init() {
            Properties props = new Properties();

            brokerList = "localhost:8081,localhost:8082,localhost:8083";
            props.put("bootstrap.servers", brokerList);
            props.put("key.serializer",
                    "some.path.to.common.serialization.ByteArraySerializer");
            props.put("value.serializer",
                    "some.path.to.common.serialization.ByteArraySerializer");
            props.put("request.required.acks", "0");

            try {
                hostname = InetAddress.getLocalHost().getHostName();
            } catch (Exception e) {
                System.out.println("Error getting Hostname, going to continue");
            }

            // Create the json object.
            StringBuilder jsonLines = new StringBuilder();

            long timestamp = 1730311218300L;
            Instant instant = Instant.ofEpochMilli(timestamp);
            LocalDateTime ldt = LocalDateTime.ofInstant(instant, zoneId);
            String date = ldt.format(dateFormat);
            String time = ldt.format(timeFormat);

            // Collect datapoints and populate the json object.
            jsonLines.append("{\"hostname\": \"" + hostname);
            jsonLines.append("\", \"timestamp\": " + timestamp);
            jsonLines.append(", \"date\": \"" + date);
            jsonLines.append("\",\"time\": \"" + time);
            jsonLines.append("\",\"name\": \"" + "something" + "\" ");
            jsonLines.append("}");

            System.out.println(jsonLines.toString());
        }

        @Override
        public void close() throws IOException {
            try {
                // do close
            } catch (RuntimeException e) {
                throw new MetricsException("Error closing producer", e);
            } finally {
                System.out.println("Error");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("--- START ---");

        var dt = new DTExample();
        dt.init();

        System.out.println("--- END ---");
    }
}
