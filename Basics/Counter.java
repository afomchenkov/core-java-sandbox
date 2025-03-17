package Basics;

public class Counter {
    private int count;

    public Counter() {
    }

    public Counter(int initial) {
        count = initial;
    }

    public int getCount() {
        return count;
    }

    public void increment() {
        count++;
    }

    public void increment(int delta) {
        count += delta;
    }

    public void reset() {
        count = 0;
    }

    public static void main(String[] ars) {
        Counter c;
        c = new Counter();
        c.increment();
        c.increment(3);
        int temp = c.getCount();
        c.reset();

        Counter d = new Counter(5);
        d.increment();
        Counter e = d;
        temp = e.getCount();
        e.increment(2);
    }
}
