package org.briarheart.algorithm;

import com.google.common.base.Stopwatch;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(Paths.get(Main.class.getClassLoader().getResource("16Kints.txt").toURI()));
        int[] ints = new int[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            ints[i] = Integer.parseInt(line);
        }

        Stopwatch stopwatch = Stopwatch.createStarted();
        int counter = 0;
        for (int i = 0; i < ints.length; i++) {
            for (int j = i + 1; j < ints.length; j++) {
                for (int k = j + 1; k < ints.length; k++) {
                    if (ints[i] + ints[j] + ints[k] == 0) {
                        counter++;
                    }
                }
            }
        }
        stopwatch.stop();

        System.out.println("Result: " + counter + " [" + stopwatch + "]");
    }
}
