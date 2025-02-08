package MultiThreading;

class A extends Thread {
    public void run() {
        System.out.println("class A start -------");
        for (int i = 0; i <= 5; i++) {
            System.out.println("A " + i);

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("class A end -------");
    }
}

class B extends Thread {
    public void run() {
        System.out.println("class B start -------");
        for (int i = 0; i <= 5; i++) {
            System.out.println("B " + i);

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("class B end -------");
    }
}

class Z implements Runnable {
    public void run() {
        System.out.println("class Z start -------");
        for (int i = 0; i <= 5; i++) {
            System.out.println("B " + i);

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("class Z end -------");
    }
}

public class MultipleThreads {
    public static void main(String[] args) {
        A obj1 = new A();
        B obj2 = new B();
        Runnable obj3 = new Z();

        Thread t1 = new Thread(obj3);
        Thread t2 = new Thread(obj3);

        t1.start();
        t2.start();

        obj1.setPriority(Thread.MAX_PRIORITY);
        obj1.start();
        obj2.start();

        Runnable rn1 = new Runnable() {
            public void run() {
                System.out.println("anon class start -------");
                for (int i = 0; i <= 5; i++) {
                    System.out.println("B " + i);

                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("anon class end -------");
            }
        };

        Runnable rn2 = () -> {
            System.out.println("lambda start -------");
            for (int i = 0; i <= 5; i++) {
                System.out.println("B " + i);

                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("lambda end -------");
        };

        Thread t3 = new Thread(rn1);
        Thread t4 = new Thread(rn2);

        t3.start();
        t4.start();
    }
}
