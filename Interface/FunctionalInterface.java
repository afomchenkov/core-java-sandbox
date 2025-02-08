package Interface;

// SAM - single abstract method interface
interface A {
    void show();
}

class B implements A {
    public void show() {
        System.out.println("Print B with A interface");
    }
}

public class FunctionalInterface {
    public static void main(String[] args) {
        A obj1 = new B();
        obj1.show();

        // anonumous class
        A obj2 = new A() {
            public void show() {
                System.out.println("Print A from anonumous class");
            }
        };
        obj2.show();
    }
}
