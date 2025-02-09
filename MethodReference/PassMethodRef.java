import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Student {
    private String name;
    private int age;

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

interface MyCode {
    double myScore();
}

public class PassMethodRef {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Navin", "Harsh", "John");
        List<String> uNames = names.stream().map(String::toUpperCase).toList();

        // System.out.println(uNames);
        uNames.forEach(System.out::println);

        List<Student> students = new ArrayList<>();
        students = names.stream().map(Student::new).toList();

        System.out.println(students);

        MyCode myScore;
        myScore = () -> 87;
        // System.out.println(myScore);
    }
}
