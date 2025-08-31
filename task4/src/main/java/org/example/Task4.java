package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task4 {
    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                arr.add(Integer.valueOf(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int res = findSteps(arr);
        if (res <= 20) {
            System.out.println(res);
        } else {
            System.out.println("20 ходов недостаточно для приведения всех элементов массива к одному числу");
        }
    }

    private static int findSteps(List<Integer> arr) {
        Collections.sort(arr);
        int m = arr.get(arr.size() / 2);
        int res = 0;
        for (Integer i : arr) {
            res += Math.abs(i - m);
        }
        return res;
    }
}