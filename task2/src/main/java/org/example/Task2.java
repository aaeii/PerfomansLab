package org.example;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Task2 {
    public static void main(String[] args) {
        String ellipse = args[0];//"src/main/java/ellipse.txt";
        String points = args[1];//"src/main/java/points.txt";
        double[] elCoord = readEllipse(ellipse);
        double[][] pointsCoord = readPoints(points);
        if (elCoord == null) {
            System.out.println("Ошибка");
            return;
        }
        double centerX = elCoord[0], centerY = elCoord[1], a = elCoord[2], b = elCoord[3];
        for (double[] p : pointsCoord) {
            double x = p[0], y = p[1];
            double distance = (Math.pow((x - centerX) / a, 2)) + (Math.pow((y - centerY) / b, 2));

            if (distance < 1) {
                System.out.println(1);
            } else if (distance == 1) {
                System.out.println(0);
            } else {
                System.out.println(2);
            }
        }
    }

    private static double[] readEllipse(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            double[] parameters = new double[4];
            if ((line = br.readLine()) != null) {
                String[] center = line.split(" ");
                parameters[0] = Double.parseDouble(center[0]);
                parameters[1] = Double.parseDouble(center[1]);
            }
            if ((line = br.readLine()) != null) {
                String[] r = line.split(" ");
                parameters[2] = Double.parseDouble(r[0]);
                parameters[3] = Double.parseDouble(r[1]);
            }
            return parameters;
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static double[][] readPoints(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            double[][] points = new double[100][2];
            int count = 0;

            while ((line = br.readLine()) != null) {
                String[] coords = line.split(" ");
                points[count][0] = Double.parseDouble(coords[0]); // x
                points[count][1] = Double.parseDouble(coords[1]); // y
                count++;
            }
            return java.util.Arrays.copyOf(points, count);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}


