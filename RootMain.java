
public class RootMain {
    interface StringFunction {
        String check(Integer str);
    }

    public class HumanReadableTime {
        public static String makeReadable(int seconds) {
            int minutesTotal = seconds / 60;
            int hours = minutesTotal / 60;

            int sec = seconds - minutesTotal * 60;
            int min = minutesTotal - hours * 60;

            StringFunction adjust = (value) -> {
                var str = Integer.toString(value);
                if (str.length() == 1) {
                    return "0" + str;
                }
                return str;
            };

            return adjust.check(hours) + ":" + adjust.check(min) + ":" + adjust.check(sec);
        }
    }

    public static void main(String[] args) {
        System.out.println(HumanReadableTime.makeReadable(5));
    }
}
