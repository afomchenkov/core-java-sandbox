package Exceptions;

public class Exceptions {
    public static void main(String[] args) {
        int i = 0;
        int j = 0;

        int nums[] = new int[5];

        try {
            j = 18 / i;
            System.out.println(j);
            System.out.println(nums[5]);
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic exception raised " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("index greater than array length");
        } catch (Exception e) {
            System.out.println("Exception raised " + e.getMessage());
        }
    }
}
