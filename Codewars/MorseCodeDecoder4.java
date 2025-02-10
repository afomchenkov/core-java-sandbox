package Codewars;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

class MorseCode {
    private static Map<String, String> dict = Map.ofEntries(
            Map.entry(".-", "A"),
            Map.entry("-...", "B"),
            Map.entry("-.-.", "C"),
            Map.entry("-..", "D"),
            Map.entry(".", "E"),
            Map.entry("..-.", "F"),
            Map.entry("--.", "G"),
            Map.entry("....", "H"),
            Map.entry("..", "I"),
            Map.entry(".---", "J"),
            Map.entry("-.-", "K"),
            Map.entry(".-..", "L"),
            Map.entry("--", "M"),
            Map.entry("-.", "N"),
            Map.entry("---", "O"),
            Map.entry(".--.", "P"),
            Map.entry("--.-", "Q"),
            Map.entry(".-.", "R"),
            Map.entry("...", "S"),
            Map.entry("-", "T"),
            Map.entry("..-", "U"),
            Map.entry("...-", "V"),
            Map.entry(".--", "W"),
            Map.entry("-..-", "X"),
            Map.entry("-.--", "Y"),
            Map.entry("--..", "Z"),
            Map.entry("-----", "0"),
            Map.entry(".----", "1"),
            Map.entry("..---", "2"),
            Map.entry("...--", "3"),
            Map.entry("....-", "4"),
            Map.entry(".....", "5"),
            Map.entry("-....", "6"),
            Map.entry("--...", "7"),
            Map.entry("---..", "8"),
            Map.entry("----.", "9"),
            Map.entry(".-.-.-", "."),
            Map.entry("--..--", ","),
            Map.entry("..--..", "?"),
            Map.entry(".----.", "\\"),
            Map.entry("-.-.--", "!"),
            Map.entry("-..-.", "/"),
            Map.entry("-.--.", "("),
            Map.entry("-.--.-", ")"),
            Map.entry(".-...", "&"),
            Map.entry("---...", ":"),
            Map.entry("-.-.-.", ";"),
            Map.entry("-...-", "="),
            Map.entry(".-.-.", "+"),
            Map.entry("-....-", "-"),
            Map.entry("..--.-", "_"),
            Map.entry(".-..-.", "\""),
            Map.entry("...-..-", "$"),
            Map.entry(".--.-.", "@"),
            Map.entry("..-.-", "¿"),
            Map.entry("--...-", "¡"));

    public static String get(String code) {
        return dict.get(code);
    }
}

/**
 * The international standard:
 * 
 * - "Dot" – is 1 time unit long.
 * - "Dash" – is 3 time units long.
 * - Pause between dots and dashes in a character – is 1 time unit long.
 * - Pause between characters inside a word – is 3 time units long.
 * - Pause between words – is 7 time units long.
 */

public class MorseCodeDecoder4 {

    public static final String WORDS_SPACE = "00000000000000";
    public static final String SPACE = "000000";

    public static final String DOT = "11";
    public static final String SEPARATOR = "00";
    public static final String DASH = "111111";

    private static Map<String, String> tokens = Map.ofEntries(
            Map.entry(DOT, "."),
            Map.entry(SEPARATOR, ""),
            Map.entry(DASH, "-"),
            Map.entry(SPACE, " "),
            Map.entry(WORDS_SPACE, "   "));

    public static String decodeBits(String bits) {
        String trimmed = bits.replaceAll("^0+|0+$", "");

        if (trimmed.length() == 0) {
            return "";
        }

        StringBuilder decoded = new StringBuilder();

        String[] binaryWords = trimmed.split(WORDS_SPACE);
        for (var bWord : binaryWords) {
            String[] word = bWord.split(SPACE);
            StringBuilder decodedWord = new StringBuilder();

            for (var bLetter : word) {
                String[] letters = bLetter.split(SEPARATOR);
                for (var letter : letters) {
                    decodedWord.append(tokens.get(letter));
                }
                decodedWord.append(tokens.get(SPACE));
            }
            // end of word, remove last space
            decodedWord.deleteCharAt(decodedWord.length() - 1);
            decoded.append(decodedWord.append(tokens.get(WORDS_SPACE)).toString());
        }

        return decoded.length() > 0 ? decoded.toString() : "";
    }

    public static String decode(String morseCode) {
        if (morseCode.length() == 0) {
            return "";
        }
        return Arrays.stream(morseCode.trim().split("   "))
                .map(MorseCodeDecoder4::decodeWord)
                .collect(Collectors.joining(" "));
    }

    private static String decodeWord(String word) {
        return Arrays.stream(word.split(" "))
                .map(MorseCode::get)
                .collect(Collectors.joining());
    }

    public static void main(String[] args) {
        // var code =
        // "1100110011001100000011000000111111001100111111001111110000000000000011001111110011111100111111000000110011001111110000001111110011001100000011";
        var code = "00000000";

        var morse = decodeBits(code);
        System.out.println("[" + morse + "]");
        var text = decode(morse);
        System.out.println("[" + text + "]");
    }
}
