package com.example.task.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dive {

    public static void main(String[] args) throws IOException {
        System.out.println("First task result: " + firstPart());
        System.out.println("Second task result: " + secondPart());
    }

    private static int secondPart() throws IOException {
        List<String> source = readFromInputStream("src/main/resources/day2/secondSource");
        int horizontal = 0;
        int depth = 0;
        int aim = 0;
        for (String line : source) {
            final String[] split = line.split(" ");
            final String operation = split[0];
            final Integer value = Integer.valueOf(split[1]);

            if (operation.equals("forward")) {
                horizontal += value;
                depth += aim * value;
            } else if (operation.equals("down")) {
                aim += value;
            } else if (operation.equals("up")) {
                aim -= value;
            }
        }
        return depth * horizontal;
    }

    private static int firstPart() throws IOException {
        List<String> source = readFromInputStream("src/main/resources/day2/firstSource");
        int horizontal = 0;
        int depth = 0;
        for (String line : source) {
            final String[] split = line.split(" ");
            final String operation = split[0];
            final Integer value = Integer.valueOf(split[1]);

            if (operation.equals("forward")) {
                horizontal += value;
            } else if (operation.equals("down")) {
                depth += value;
            } else if (operation.equals("up")) {
                depth -= value;
            }
        }
        return depth * horizontal;
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
