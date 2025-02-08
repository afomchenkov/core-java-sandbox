package Exceptions;

class A {
    public void show() throws ClassNotFoundException {
        // try {
        //     Class.forName("Demo");
        // } catch (ClassNotFoundException e) {
        //     System.out.println("Exception: " + e.getMessage());
        // }

        Class.forName("Demo");
    }
}

public class DuckingExceptions {
    public static void main(String[] args) {
        A obj1 = new A();

        try {
            obj1.show();
        } catch (ClassNotFoundException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
