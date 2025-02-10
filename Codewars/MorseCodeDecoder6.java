package Codewars;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

class MorseCode {
    private static Map<String, Character> dict = Map.ofEntries(
            Map.entry(".-", 'A'),
            Map.entry("-...", 'B'),
            Map.entry("-.-.", 'C'),
            Map.entry("-..", 'D'),
            Map.entry(".", 'E'),
            Map.entry("..-.", 'F'),
            Map.entry("--.", 'G'),
            Map.entry("....", 'H'),
            Map.entry("..", 'I'),
            Map.entry(".---", 'J'),
            Map.entry("-.-", 'K'),
            Map.entry(".-..", 'L'),
            Map.entry("--", 'M'),
            Map.entry("-.", 'N'),
            Map.entry("---", 'O'),
            Map.entry(".--.", 'P'),
            Map.entry("--.-", 'Q'),
            Map.entry(".-.", 'R'),
            Map.entry("...", 'S'),
            Map.entry("-", 'T'),
            Map.entry("..-", 'U'),
            Map.entry("...-", 'V'),
            Map.entry(".--", 'W'),
            Map.entry("-..-", 'X'),
            Map.entry("-.--", 'Y'),
            Map.entry("--..", 'Z'),
            Map.entry("-----", '0'),
            Map.entry(".----", '1'),
            Map.entry("..---", '2'),
            Map.entry("...--", '3'),
            Map.entry("....-", '4'),
            Map.entry(".....", '5'),
            Map.entry("-....", '6'),
            Map.entry("--...", '7'),
            Map.entry("---..", '8'),
            Map.entry("----.", '9'),
            Map.entry(".-.-.-", '.'),
            Map.entry("--..--", ','),
            Map.entry("..--..", '?'),
            Map.entry(".----.", '\\'),
            Map.entry("-.-.--", '!'),
            Map.entry("-..-.", '/'),
            Map.entry("-.--.", '('),
            Map.entry("-.--.-", ')'),
            Map.entry(".-...", '&'),
            Map.entry("---...", ':'),
            Map.entry("-.-.-.", ';'),
            Map.entry("-...-", '='),
            Map.entry(".-.-.", '+'),
            Map.entry("-....-", '-'),
            Map.entry("..--.-", '_'),
            Map.entry(".-..-.", '"'),
            Map.entry("...-..-", '$'),
            Map.entry(".--.-.", '@'),
            Map.entry("..-.-", '¿'),
            Map.entry("--...-", '¡'));

    public static Character get(String code) throws Exception {
        if (!dict.containsKey(code)) {
            throw new Exception("Unsupported code");
        }
        return dict.get(code);
    }
}

public class MorseCodeDecoder6 {
    public static String decode(String morseCode) {
        String[] words = morseCode.trim().split("   ");
        String result = "";

        for (var word : words) {
            String[] letters = word.split(" ");
            var decoded = Arrays.asList(letters).stream().map((code) -> {
                try {
                    var letter = MorseCode.get(code);
                    return String.valueOf(letter);
                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage());
                }

                return "NONE";
            })
                    .filter((letter) -> !"NONE".equals(letter))
                    .reduce(new StringBuilder(), (sb, str) -> sb.append(str), StringBuilder::append).toString();
            result += " " + decoded;
        }

        return result.trim();
    }

    // public static String decode(String morseCode) {
    //     return Arrays.stream(morseCode.trim().split("   "))
    //             .map(MorseCodeDecoder::decodeWord)
    //             .collect(Collectors.joining(" "));
    // }

    // private static String decodeWord(String word) {
    //     return Arrays.stream(word.split(" ")).map(MorseCode::get).collect(Collectors.joining());
    // }

    public static void main(String[] args) {
        System.out.println("---- start ----");

        var result = MorseCodeDecoder6.decode(".... . -.--   .--- ..- -.. .");

        System.out.println(result);

        System.out.println("---- end ----");
    }
}
