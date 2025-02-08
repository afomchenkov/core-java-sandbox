package Exceptions;

/**
 *            Object
 *              |
 *          Throwable
 *          /       \
 *       Error     Exception
 *      / | \          |     \
 * ThreadDeath      Runtime   SQLException
 *      IOError        |            |
 *          ....       |            |
 *                     |      (All) Checked Exceptions
 *                     |
 *          (All) Unchecked Exceptions
 *
 */

class MyException extends Exception {
    String message;

    public MyException(String message) {
        super(message);
    }
}

public class ExceptionsHierarchy {
    public static void main(String[] srgs) {
        int i = 0;
        int j = 0;

        try {
            j = 18 / i;

            if (j == 0) {
                // throw new ArithmeticException();
                throw new MyException("something has happened");
            }
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic exception raised " + e.getMessage());
        } catch (MyException e) {
            System.out.println("Arithmetic exception raised " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception raised " + e.getMessage());
        }
    }
}
