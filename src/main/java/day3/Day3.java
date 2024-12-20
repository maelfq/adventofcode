package day3;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static common.CommonFileUtils.readFromInputStream;

public class Day3 {
    public static void main(String[] args) {

        try {
            System.out.println("Result: " + retrieveMultiplicationsSum());
            System.out.println("Result with disabled parts: " + retrieveMultiplicationsSumWithDisabledParts());
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public static long retrieveMultiplicationsSum() throws IOException {
        ArrayList<String> input = parseCorruptedInput();
        long sum = computeSumOfMultiplications(input);
        return sum;
    }

    public static long retrieveMultiplicationsSumWithDisabledParts() throws IOException {
        ArrayList<String> input = parseCorruptedInputWithDisabledParts();
        long sum = computeSumOfMultiplications(input);
        return sum;
    }

    public static long computeSumOfMultiplications(ArrayList<String> input) {
        long sum = 0;
        for (String multiplication : input) {
            long[] numbers = parseMultiplicationInput(multiplication);
            long multiplicationResult = numbers[0] * numbers[1];
            sum += multiplicationResult;
        }
        return sum;
    }

    public static long[] parseMultiplicationInput(String multiplication) {
        long[] input = {0, 0};
        Pattern pattern = Pattern.compile("[0-9]{1,3},[0-9]{1,3}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(multiplication);
        boolean foundMatch = matcher.find();
        if (foundMatch) {
            String group = matcher.group();
            String[] numbers = group.split(",");

            if (numbers.length == 2) {
                input[0] = Long.parseLong(numbers[0]);
                input[1] = Long.parseLong(numbers[1]);
            }
        }

        return input;
    }

    public static ArrayList<String> parseCorruptedInput() throws IOException {
        String fileName = "day3-input.txt";
        ClassLoader classLoader = Day3.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        String data = readFromInputStream(inputStream);

        ArrayList<String> multiplications = getMultiplications(data);

        return multiplications;
    }

    public static ArrayList<String> parseCorruptedInputWithDisabledParts() throws IOException {
        String fileName = "day3-input.txt";
        ClassLoader classLoader = Day3.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        String data = readFromInputStream(inputStream);

        ArrayList<String> correctedInput= getEnabledParts(data);
        return correctedInput;
    }

    private static ArrayList<String> getMultiplications(String input) {
        Pattern pattern = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        ArrayList<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        return matches;
    }

    private static ArrayList<String> getEnabledParts(String input) {
        ArrayList<String> enabledParts = new ArrayList<>();

        Pattern pattern = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)|do\\(\\)|don't\\(\\)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        boolean isEnabled = true;
        while (matcher.find()) {
            String match = matcher.group();
            if (match.equals("do()")) {
                isEnabled = true;
            } else if (match.equals("don't()")) {
                isEnabled = false;
            } else {
                if (isEnabled) {
                    enabledParts.add(match);
                }
            }
        }

        return enabledParts;
    }
}
