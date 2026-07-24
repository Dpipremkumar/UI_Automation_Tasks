package interview_Question;

public class ConvertToNumericValue {

    public static void main(String[] args) {
        Long num = convertStringtoNumericValue("74.3433434b");
        System.out.println(num);
    }

    public static long convertStringtoNumericValue(String value) {
        value = value.toUpperCase();
        long multiplier = 1;

        if (value.endsWith("K")) {
            multiplier = 1_000L;
            value = value.replace("K", "");
        } else if (value.endsWith("M")) {
            multiplier = 1_000_000L;
            value = value.replace("M", "");
        } else if (value.endsWith("B")) {
            multiplier = 1_000_000_000L;
            value = value.replace("B", "");
        }
        long num = (long) (Double.parseDouble(value) * multiplier);
        return num;
    }
}
