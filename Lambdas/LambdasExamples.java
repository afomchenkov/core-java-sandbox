package Lambdas;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

interface Processor {
    String process(Callable<String> c) throws Exception;

    String process(Supplier<String> s);
}

@FunctionalInterface
interface Baz {
    String method(String string);

    default String defaultBaz() {
        return "";
    }

    default String defaultCommon() {
        return "";
    }
}

@FunctionalInterface
interface Bar {
    String method(String string);

    default String defaultBaz() {
        return "";
    }

    default String defaultCommon() {
        return "";
    }
}

// @FunctionalInterface
// interface FooExtended extends Baz, Bar {
//     @Override
//     default String defaultCommon() {
//         return Bar.super.defaultCommon();
//     }
// }

@FunctionalInterface
interface StringFunction {
    String run(String str);
}

@FunctionalInterface
interface Foo {
    String method(String string);
}

public class LambdasExamples {
    String value;

    public class ProcessorImpl implements Processor {
        @Override
        public String process(Callable<String> c) throws Exception {
            // implementation details
            return "";
        }

        @Override
        public String process(Supplier<String> s) {
            // implementation details
            return "";
        }
    }

    public class Inner {
        public String doSomething(String str, Foo foo) {
            return foo.method(str);
        }

        public String doSomethingElse(String str, Function<String, String> fn) {
            return fn.apply(str);
        }

        public String something() {
            Foo foo = parameter -> parameter + " from lambda";
            Function<String, String> bar = parameter -> parameter + " from lambda";
            return this.doSomething("Message ", foo) + ":" + this.doSomethingElse("Another Message ", bar);
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(5);
        numbers.add(9);
        numbers.add(8);
        numbers.add(1);

        // numbers.forEach((n) -> {
        //     System.out.println(n);
        // });

        Consumer<Integer> method = (n) -> {
            System.out.println(n);
        };
        numbers.forEach(method);

        StringFunction exclaim = (s) -> s + "!";
        StringFunction ask = (s) -> s + "?";
        printFormatted("Hello", exclaim);
        printFormatted("Hello", ask);
    }

    public static void printFormatted(String str, StringFunction format) {
        String result = format.run(str);
        System.out.println(result);

    }

    public void run() {
        // lambda example
        Foo fooLambda = parameter -> {
            String value = "Lambda value";
            return this.value;
        };
        String resultLambda = fooLambda.method("");

        // inner class example
        Foo fooIC = new Foo() {
            String value = "Inner class value";

            @Override
            public String method(String string) {
                return string + " from Foo " + this.value;
            }
        };
        String resultIC = fooIC.method("--");
    }
}
