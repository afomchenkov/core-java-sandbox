package Lambdas;

import BlochExamples.main;

@FunctionalInterface
interface A {
    public void show();
}

@FunctionalInterface
interface B {
    public void show(int i);
}

public class LambdaExpression {
    public static void main(String[] args) {
        A obj1 = () -> {
            System.out.println("Print from lambda");
        };
        obj1.show();

        A obj2 = () -> System.out.println("short from lambda expression");
        obj2.show();

        B obj3 = i -> System.out.println("short from lambda with param " + i);
        obj3.show(3);
    }
}
