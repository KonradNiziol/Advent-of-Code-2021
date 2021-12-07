package com.example.task.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryDiagnostic {

    public static void main(String[] args) throws IOException {
        System.out.println("First task result: " + firstPart());
        System.out.println("Second task result: " + secondPart());
    }

    private static int secondPart() throws IOException {
        List<String> source = readFromInputStream("src/main/resources/day3/binaryDiagnostic");

        String gammaRate = oxygenGeneratorRating(source);
        String epsilonRate = CO2ScrubberRating(source);

        int result = Integer.parseInt(gammaRate, 2) * Integer.parseInt(epsilonRate, 2);
        return result;
    }

    private static String oxygenGeneratorRating(List<String> source) {

        int index = 0;
        List<String> restlt = new ArrayList<>(source);
        while (restlt.size() > 1) {
            final List<Integer> occurrencesInEachIndex = countTheOccurrencesInEachIndex(restlt);
            final Integer integer = occurrencesInEachIndex.get(index);
                int finalIndex = index;
            if (integer >= 0) {
                restlt.removeIf(s -> s.charAt(finalIndex) == '0');
            } else {
                restlt.removeIf(s -> s.charAt(finalIndex) == '1');
            }
            index++;
        }
        return restlt.get(0);
    }

    private static String CO2ScrubberRating(List<String> source) {

        int index = 0;
        List<String> restlt = new ArrayList<>(source);
        while (restlt.size() > 1) {
            final List<Integer> occurrencesInEachIndex = countTheOccurrencesInEachIndex(restlt);
            final Integer integer = occurrencesInEachIndex.get(index);
                int finalIndex = index;
            if (integer < 0) {
                restlt.removeIf(s -> s.charAt(finalIndex) == '0');
            } else {
                restlt.removeIf(s -> s.charAt(finalIndex) == '1');
            }
            index++;
        }
        return restlt.get(0);
    }

    private static int firstPart() throws IOException {
        final List<String> source = readFromInputStream("src/main/resources/day3/binaryDiagnostic");

        final List<Integer> occurrences = countTheOccurrencesInEachIndex(source);
        StringBuilder gammaRate = new StringBuilder();
        StringBuilder epsilonRate = new StringBuilder();
        for (Integer val : occurrences) {
            if (val > 0) {
                gammaRate.append("1");
                epsilonRate.append("0");
            } else {
                gammaRate.append("0");
                epsilonRate.append("1");
            }
        }
        int result = Integer.parseInt(gammaRate.toString(), 2) * Integer.parseInt(epsilonRate.toString(), 2);
        return result;
    }

    private static List<Integer> countTheOccurrencesInEachIndex(final List<String> source) {
        List<Integer> result = new LinkedList<>();

        for (int i = 0; i < source.get(0).length(); i++) {
            result.add(0);
        }

        for (String line : source) {
            final String[] split = line.split("");
            for (int i = 0; i < split.length; i++) {
                final Integer value = Integer.valueOf(split[i]);
                final Integer counter = result.get(i);
                int newValue;
                if (value == 1) {
                    newValue = counter + 1;
                } else {
                    newValue = counter - 1;
                }
                result.set(i, newValue);
            }
        }
        return result;
    }

    private static List<String> readFromInputStream(final String path)
            throws IOException {
        List<String> result = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line != null) {
                result.add(line);
                line = br.readLine();
            }
        }
        return result;
    }
}
