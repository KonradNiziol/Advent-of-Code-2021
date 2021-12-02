package com.example.task.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SonarSweep {

    public static void main(String[] args) throws IOException {
        System.out.println("First task result: " + firstPart());
        System.out.println("Second task result: " + secondPart());
    }

    private static int secondPart() throws IOException {
        List<Integer> source = readFromInputStream("src/main/java/com/example/task/day1/sonarSweepSecondTask");
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < source.size(); i++) {
            if (i + 2 < source.size()) {
                result.add(source.get(i) + source.get(i+1) + source.get(i+2));
            }
        }
        return calculateNumberOfIncreasedValue(result);
    }

    private static int firstPart() throws IOException {
        List<Integer> source = readFromInputStream("src/main/java/com/example/task/day1/sonarSweep");
        return calculateNumberOfIncreasedValue(source);
    }

    private static int calculateNumberOfIncreasedValue(final List<Integer> values) {
        int result = 0;
        Integer lastValue = Integer.MAX_VALUE;
        for (Integer value : values) {
            if (value > lastValue) {
                result++;
            }
            lastValue = value;
        }
        return result;
    }

    private static List<Integer> readFromInputStream(final String path)
            throws IOException {
        List<Integer> result = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line != null) {
                result.add(Integer.valueOf(line));
                line = br.readLine();
            }
        }
        return result;
    }
}
