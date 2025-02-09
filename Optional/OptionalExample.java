package Optional;
import java.util.*;

public class OptionalExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Navin", "Laxmi", "John", "Kishor");

        Optional<String> name = names.stream().filter(str -> str.contains("x")).findFirst();

        System.out.println(name.orElse("Nothing found"));
    }
}
