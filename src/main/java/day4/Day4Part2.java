package day4;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static common.CommonFileUtils.readFromInputStream;

public class Day4Part2 {

    public static void main(String[] args) throws IOException {
        System.out.println("Count of XMAS words: " + retrieveXmasCount());
    }

    public static int retrieveXmasCount() throws IOException {
        ArrayList<ArrayList<String>> input = parseXmasInput();
        return countXmas(input);
    }

    public static int countXmas(ArrayList<ArrayList<String>> input) {
        int numberOfLines = input.size();
        int totalCount = 0;
        for (int lineIndex = 0; lineIndex < numberOfLines; lineIndex++) {
            int numberOfCharacters = input.get(lineIndex).size();
            for (int characterIndex = 0; characterIndex < numberOfCharacters; characterIndex++) {
                if (input.get(lineIndex).get(characterIndex).equals("A")) {
                    int xmasCount = getXmasCountOfCurrentPosition(lineIndex, characterIndex, input);
                    totalCount += xmasCount;
                }
            }
        }
        return totalCount;
    }

    public static int getXmasCountOfCurrentPosition(int lineIndex, int characterIndex, ArrayList<ArrayList<String>> input) {
        String word = "MAS";
        boolean areCoordinatesOfDiagonalsReachable = areCoordinatesOfXmasInsideArray(lineIndex, characterIndex, word, input);

        if(areCoordinatesOfDiagonalsReachable) {

            // from top left to down right diagonal
            StringBuilder firstDiagonalStringBuilder = new StringBuilder();

            // from down left to top right diagonal
            StringBuilder secondDiagonalStringBuilder = new StringBuilder();

            int distance = getDistanceFromMiddleOfWord(word);
            int wordLength = word.length();

            int charIndexOfLeftPart = characterIndex - distance;
            int charIndexOfRightPart = characterIndex + distance;

            int lineIndexOfTopPart = lineIndex - distance;
            int lineIndexOfBottomPart = lineIndex + distance;

            for(int i = 0; i < wordLength; i++) {
                firstDiagonalStringBuilder.append(input.get(lineIndexOfTopPart + i).get(charIndexOfLeftPart + i));
                secondDiagonalStringBuilder.append(input.get(lineIndexOfBottomPart - i).get(charIndexOfLeftPart + i));
            }
            if((firstDiagonalStringBuilder.toString().equals(word) || firstDiagonalStringBuilder.reverse().toString().equals(word))
            && (secondDiagonalStringBuilder.toString().equals(word) || secondDiagonalStringBuilder.reverse().toString().equals(word))) {
                System.out.println("+1");
                return 1;
            }
        }
        return 0;
    }

    public static boolean areCoordinatesOfXmasInsideArray(int lineIndex, int characterIndex, String word, ArrayList<ArrayList<String>> input) {
        boolean result = false;
        int yLength = input.size();
        int xLength = input.get(lineIndex).size();
        int distance = getDistanceFromMiddleOfWord(word);

        int charIndexOfLeftPart = characterIndex - distance;
        int charIndexOfRightPart = characterIndex + distance;

        int lineIndexOfTopPart = lineIndex - distance;
        int lineIndexOfBottomPart = lineIndex + distance;

        if(charIndexOfLeftPart >= 0 && lineIndexOfTopPart >= 0
        && charIndexOfRightPart < xLength && lineIndexOfBottomPart < yLength) {
            result = true;
        }
        System.out.println("X: " + characterIndex + ", Y: " + lineIndex + ", Result: " + result);
        return result;
    }

    public static int getDistanceFromMiddleOfWord(String word) {
        int size = word.length();
        int middleIndex = size / 2;
        return middleIndex;
    }

    public static ArrayList<ArrayList<String>> parseXmasInput() throws IOException {
        String fileName = "day4-input.txt";
        ClassLoader classLoader = Day4Part1.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        String data = readFromInputStream(inputStream);
        String[] lines = data.split("\n");


        ArrayList<ArrayList<String>> xmasInput = new ArrayList<>();
        for (String line : lines) {
            ArrayList<String> charactersOfLine = new ArrayList<>();
            for (char c : line.toCharArray()) {
                charactersOfLine.add(String.valueOf(c));
            }
            xmasInput.add(charactersOfLine);
        }

        return xmasInput;
    }
}
