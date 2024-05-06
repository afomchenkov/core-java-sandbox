package Exceptions;

import java.io.*;

// Java exceptions fall into two main categories: checked exceptions and unchecked exceptions.
//
// - Java verifies checked exceptions at compile-time.
// Therefore, we should use the 'throws' keyword to declare a checked exception
// Some common checked exceptions in Java are IOException, SQLException and ParseException.
//
//
// - Java does not verify unchecked exceptions at compile-time.
// If a program throws an unchecked exception, it reflects some error inside the program logic.
// we don’t have to declare unchecked exceptions in a method with the 'throws' keyword.
// Some common unchecked exceptions in Java are NullPointerException, ArrayIndexOutOfBoundsException and IllegalArgumentException.
//
// “If a client can reasonably be expected to recover from an exception, make it a checked exception.
// If a client cannot do anything to recover from the exception, make it an unchecked exception.”
//
// ------------------------------------------------------------------------------------------------
//
// For example, before we open a file, we can first validate the input file name. If the user
// input file name is invalid, we can throw a custom checked exception:
//
// if (!isCorrectFileName(fileName)) {
//     throw new IncorrectFileNameException("Incorrect filename : " + fileName );
// }
//
// In this way, we can recover the system by accepting another user input file name.
//
// However, if the input file name is a null pointer or it is an empty string, it means
// that we have some errors in the code. In this case, we should throw an unchecked exception:
//
// if (fileName == null || fileName.isEmpty())  {
//     throw new NullOrEmptyException("The filename is null or empty.");
// }

public class ExceptionsExample {

    class FileReader {
        // checked exception
        public FileInputStream checkedExceptionWithThrows() throws FileNotFoundException {
            File file = new File("not_existing_file.txt");
            FileInputStream stream = new FileInputStream(file);
            return stream;
        }

        // unchecked exception
        public FileInputStream checkedExceptionWithTryCatch() {
            File file = new File("not_existing_file.txt");

            try {
                FileInputStream stream = new FileInputStream(file);
                System.out.println(stream.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    public class IncorrectFileNameException extends Exception {
        public IncorrectFileNameException(String errorMessage) {
            super(errorMessage);
        }
    }

    static void checkAge(int age) {
        if (age < 18) {
            throw new ArithmeticException("Access denied - You must be at least 18 years old.");
        } else {
            System.out.println("Access granted - You are old enough!");
        }
    }

    public static void main(String[] args) {

        try {
            int[] myNumbers = { 1, 2, 3 };
            System.out.println(myNumbers[10]);
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        } finally {
            System.out.println("The 'try catch' is finished.");
        }
    }
}
