package day4;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static common.CommonFileUtils.readFromInputStream;

public class Day4 {

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
                if (input.get(lineIndex).get(characterIndex).equals("X")) {
                    int xmasCount = getXmasCountOfCurrentPosition(lineIndex, characterIndex, input);
                    totalCount += xmasCount;
                }
            }
        }
        return totalCount;
    }

    public static int getXmasCountOfCurrentPosition(int lineIndex, int characterIndex, ArrayList<ArrayList<String>> input) {
        String word = "XMAS";
        int yLength = input.size();
        int xLength = input.get(lineIndex).size();
        int countOfPositionXY = 0;
        int numberOfLettersRequiredToCompleteWord = word.length() - 1;
        int xIndexOfLastLetter;
        int yIndexOfLastLetter;

        // X AXIS

        // To the left
        xIndexOfLastLetter = characterIndex - numberOfLettersRequiredToCompleteWord;
        if (xIndexOfLastLetter >= 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                stringBuilder.append(input.get(lineIndex).get(characterIndex - i));
            }
            String currentWord = stringBuilder.toString();
            if (currentWord.equals(word)) {
                countOfPositionXY += 1;
            }
        }

        // To the right
        xIndexOfLastLetter = characterIndex + numberOfLettersRequiredToCompleteWord;
        if (xIndexOfLastLetter < xLength) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                stringBuilder.append(input.get(lineIndex).get(characterIndex + i));
            }
            String currentWord = stringBuilder.toString();
            if (currentWord.equals(word)) {
                countOfPositionXY += 1;
            }
        }

        // Y AXIS

        // To the top
        yIndexOfLastLetter = lineIndex - numberOfLettersRequiredToCompleteWord;
        if (yIndexOfLastLetter >= 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                stringBuilder.append(input.get(lineIndex - i).get(characterIndex));
            }
            String currentWord = stringBuilder.toString();
            if (currentWord.equals(word)) {
                countOfPositionXY += 1;
            }
        }

        // To the bottom
        yIndexOfLastLetter = lineIndex + numberOfLettersRequiredToCompleteWord;
        if (yIndexOfLastLetter < yLength) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                stringBuilder.append(input.get(lineIndex + i).get(characterIndex));
            }
            String currentWord = stringBuilder.toString();
            if (currentWord.equals(word)) {
                countOfPositionXY += 1;
            }
        }

        // DIAGONALS

        // To the left up
        xIndexOfLastLetter = characterIndex - numberOfLettersRequiredToCompleteWord;
        yIndexOfLastLetter = lineIndex - numberOfLettersRequiredToCompleteWord;

        if (xIndexOfLastLetter >= 0 && yIndexOfLastLetter >= 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                stringBuilder.append(input.get(lineIndex - i).get(characterIndex - i));
            }
            String currentWord = stringBuilder.toString();
            if (currentWord.equals(word)) {
                countOfPositionXY += 1;
            }
        }

        // To the left down
        xIndexOfLastLetter = characterIndex - numberOfLettersRequiredToCompleteWord;
        yIndexOfLastLetter = lineIndex + numberOfLettersRequiredToCompleteWord;

        if (xIndexOfLastLetter >= 0 && yIndexOfLastLetter < yLength) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                stringBuilder.append(input.get(lineIndex + i).get(characterIndex - i));
            }
            String currentWord = stringBuilder.toString();
            if (currentWord.equals(word)) {
                countOfPositionXY += 1;
            }
        }

        // To the right up
        xIndexOfLastLetter = characterIndex + numberOfLettersRequiredToCompleteWord;
        yIndexOfLastLetter = lineIndex - numberOfLettersRequiredToCompleteWord;

        if (xIndexOfLastLetter < xLength && yIndexOfLastLetter >= 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                stringBuilder.append(input.get(lineIndex - i).get(characterIndex + i));
            }
            String currentWord = stringBuilder.toString();
            if (currentWord.equals(word)) {
                countOfPositionXY += 1;
            }
        }

        // To the right down
        xIndexOfLastLetter = characterIndex + numberOfLettersRequiredToCompleteWord;
        yIndexOfLastLetter = lineIndex + numberOfLettersRequiredToCompleteWord;

        if (xIndexOfLastLetter < xLength && yIndexOfLastLetter < yLength) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                stringBuilder.append(input.get(lineIndex + i).get(characterIndex + i));
            }
            String currentWord = stringBuilder.toString();
            if (currentWord.equals(word)) {
                countOfPositionXY += 1;
            }
        }

        return countOfPositionXY;
    }

    public static ArrayList<ArrayList<String>> parseXmasInput() throws IOException {
        String fileName = "day4-input.txt";
        ClassLoader classLoader = Day4.class.getClassLoader();
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
