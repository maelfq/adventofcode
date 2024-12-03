package day2;

import day1.Day1;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static common.CommonFileUtils.readFromInputStream;

public class Day2 {
    public static void main(String[] args) {
        System.out.println("Hello, world!");
        try {
            System.out.println(getCountOfSafeReports());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Integer getCountOfSafeReports() throws IOException {
        ArrayList<ArrayList<Integer>> reports = parseReportsInputAsArray();

        for(ArrayList<Integer> report : reports) {
            Integer lastNumber = null;
            boolean isAscending = false;
            boolean isPreviousAscending = false;
            Integer difference = 0;
            for(Integer currentNumber : report) {
                //TODO
                if(lastNumber != null) {
                    difference = currentNumber - lastNumber;
                    // isTheTrendTheSame
                    // isTheDistanceValid
                    if(difference > 0) {
                        isAscending = true;
                    }
                    else {
                        isAscending = false;
                    }
                }
                lastNumber = currentNumber;
            }
        }
        return -1;
    }

    public static ArrayList<ArrayList<Integer>> parseReportsInputAsArray() throws IOException {
        String fileName = "day2-input.txt";
        ClassLoader classLoader = Day1.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        String data = readFromInputStream(inputStream);
        String[] lines = data.split("\n");

        ArrayList<ArrayList<Integer>> reports = new ArrayList<>();
        ArrayList<Integer> report;
        for(String line : lines) {
            report = new ArrayList<>();
            line = line.trim();
            String[] reportNumbers = line.split(" ");
            for(String numberString : reportNumbers) {
                report.add(Integer.valueOf(numberString));
            }
            reports.add(report);
        }
        return reports;
    }

}
