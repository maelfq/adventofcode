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
            ArrayList<ArrayList<Integer>> reports = parseReportsInputAsArray();
            System.out.println(getCountOfSafeReports(reports));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Integer getCountOfSafeReports(ArrayList<ArrayList<Integer>> reports) throws IOException {
        ArrayList<Boolean> reportValidityList = getReportValidityList(reports);
        int count = 0;
        for(boolean isCurrentReportValid : reportValidityList) {
            if(isCurrentReportValid) {
                count++;
            }
        }
        return count;
    }

    private static ArrayList<Boolean> getReportValidityList(ArrayList<ArrayList<Integer>> reports) {
        ArrayList<Boolean> reportValidityList = new ArrayList<>();

        for(ArrayList<Integer> report : reports) {
            boolean isReportValid = isReportValid(report);
            reportValidityList.add(isReportValid);
        }
        return reportValidityList;
    }

    private static boolean isReportValid(ArrayList<Integer> report) {

        boolean isReportValid = true;
        Integer lastNumber = null;
        TrendOfReportEnum previousTrend = TrendOfReportEnum.UNDEFINED;
        TrendOfReportEnum currentTrend = TrendOfReportEnum.UNDEFINED;

        for(Integer currentNumber : report) {

            // Condition met when initialized
            if(lastNumber != null) {
                boolean isDistanceValid = isDistanceValid(lastNumber, currentNumber);
                currentTrend = getTrendOfReport(lastNumber, currentNumber);
                boolean hasTrendChanged = hasTrendChanged(previousTrend, currentTrend);
                isReportValid = isDistanceValid && !hasTrendChanged;
            }

            if(!isReportValid) {
                return false;
            }
            lastNumber = currentNumber;
            previousTrend = currentTrend;
        }
        return isReportValid;
    }

    private static TrendOfReportEnum getTrendOfReport(Integer lastNumber, Integer currentNumber) {
        Integer distance = currentNumber - lastNumber;
        if(distance < 0) {
            return TrendOfReportEnum.DESCENDING;
        }
        else if(distance > 0) {
            return TrendOfReportEnum.ASCENDING;
        }
        else {
            return TrendOfReportEnum.CONSTANT;
        }
    }

    private static boolean hasTrendChanged(TrendOfReportEnum previousTrend, TrendOfReportEnum currentTrend) {
        if(previousTrend.equals(currentTrend) || previousTrend.equals(TrendOfReportEnum.UNDEFINED)) {
            return false;
        }
        return true;
    }

    private static boolean isDistanceValid(Integer previousNumber, Integer currentNumber) {
        Integer distance = currentNumber - previousNumber;
        if(distance.equals(0) || Math.abs(distance) > 3) {
            return false;
        }
        return true;
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


