package Generics.GenericsExample1;

interface B {
    public void execute();
}

class A implements B {
    public void execute() {
        System.out.println("Inside class A");
    }
}

class MultipleBound<T extends A & B> {
    private T objRef;

    public MultipleBound(T obj) {
        this.objRef = obj;
    }

    public void run() {
        this.objRef.execute();
    }
}
public class Boundaries {

    public static void main(String a[]) {
        A clientObj = new A();
        MultipleBound<A> obj = new MultipleBound<A>(clientObj);
        obj.run();
    }
}
