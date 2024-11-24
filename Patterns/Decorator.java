package Patterns;

interface HttpHandler {
    void handleRequest();
}

class BasicHttpHandler implements HttpHandler {
    public void handleRequest() { System.out.println("Handle HTTP request"); }
}

class LoggingHandler implements HttpHandler {
    private HttpHandler handler;
    public LoggingHandler(HttpHandler handler) { this.handler = handler; }
    public void handleRequest() {
        System.out.println("Logging request");
        handler.handleRequest();
    }
}

/**
 * Decorator — structural pattern, dynamically adds new responsibilities to objects, doing the "decoration".
 *              This is helpful for functionality extenion without changing existing code.
 *
 * - To dynamically add the behavior to objects without changing the code
 * - To separate functionality into separate classes to handle the creation of the monolith classes with many responsibilities
 * - When we need to add new functionality to all ancestors but not to a single object
 *
 * Benefits:
 * - Easy to extend the objects behavior on the fly, without changing the source class code
 * - Reduce complexity by eliminating the need to create complex subclasses with many variations
 * - Combine several decorators to get more complex behavior, preserving the structure of the system modular and simple
 * - Single responsibility principle, any new functionality is incapsulated into a separate class-decorator
 *
 * Drawbacks:
 * - Harder to debug as many wrappers makes it more complex
 * - Creation of many small wrappers-decorators, which increases the memory consumption
 */

public class Decorator {
    public static void main(String[] args) {
        HttpHandler handler = new LoggingHandler(new BasicHttpHandler());
        handler.handleRequest();
    }
}
