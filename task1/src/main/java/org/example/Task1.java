package org.example;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Массив 1:");
        int n1 = in.nextInt(), m1 = in.nextInt();
        System.out.println("Массив 2:");
        int n2 = in.nextInt(), m2 = in.nextInt();
        int[] arr1 = new int[n1];
        for (int i = 0; i < n1; i++) {
            arr1[i] = i + 1;
        }
        int[] arr2 = new int[n2];
        for (int i = 0; i < n2; i++) {
            arr2[i] = i + 1;
        }
        path(n1,m1);
        path(n2,m2);

    }
    private static void path(int n, int m){
        int i = 1;
        do {
            System.out.print(i);
            i = 1 + (i + m - 2) % n;

        } while (i != 1);
    }
}