package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            int[][] columnOfFileAsArray = getColumnsOfFileAsArray();
            int[] list1 = columnOfFileAsArray[0];
            int[] list2 = columnOfFileAsArray[1];
            int distance = getDistanceBetweenTwoUnsortedArraysOfInteger(list1,list2);
            int score = getSimilarityScore(list1,list2);
            System.out.printf("Hello and welcome!\n");
            System.out.printf(String.valueOf(distance)+"\n");
            System.out.printf(String.valueOf(score));

        }
        catch (Exception exception) {
            System.out.println(exception);;
        }
    }

    public static int getDistanceBetweenTwoUnsortedArraysOfInteger(int[] list1, int[] list2) {
        int distance = 0;
        list1 = Arrays.stream(list1).sorted().toArray();
        list2 = Arrays.stream(list2).sorted().toArray();
        int length = list1.length;
        if (list1.length == list2.length) {
            for (int i = 0; i < length; i++) {
                distance += Math.abs(list1[i] - list2[i]);
            }
        }
        return distance;
    }

    public static int getSimilarityScore(int[] list1, int[] list2) {
        int score = 0;
        HashMap<Integer, Integer> mapOfTimesNumbersAreRepeatedInArray = getMapOfTimesNumbersAreRepeatedInArray(list2);
        for(int value : list1) {
            int factor = mapOfTimesNumbersAreRepeatedInArray.getOrDefault(value,0);
            score += value * factor;
        }
        return score;
    }

    public static HashMap<Integer,Integer> getMapOfTimesNumbersAreRepeatedInArray(int[] array) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int value : array) {
            if (!map.containsKey(value)) {
                map.put(value,1);
            }
            else {

                map.put(value, map.get(value) + 1);
            }
        }
        return map;
    }

    public static int[][] getColumnsOfFileAsArray() throws IOException {
        String fileName = "day1-input.txt";
        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        String data = readFromInputStream(inputStream);
        String[] lines = data.split("\n");

        ArrayList<Integer> column1 = new ArrayList<>();
        ArrayList<Integer> column2 = new ArrayList<>();
        for(String line : lines) {
            String[] valuesOfLine = line.split("  ");
            if(valuesOfLine.length == 2) {
                Integer valueOfFirstColumn = Integer.valueOf(valuesOfLine[0].trim());
                Integer valueOfSecondColumn = Integer.valueOf(valuesOfLine[1].trim());
                column1.add(valueOfFirstColumn);
                column2.add(valueOfSecondColumn);
            }
        }
        int[] col1 = column1.stream().mapToInt(value -> value).toArray();
        int[] col2 = column2.stream().mapToInt(value -> value).toArray();
        int[][] result = {col1,col2};

        return result;
    }

    private static String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}