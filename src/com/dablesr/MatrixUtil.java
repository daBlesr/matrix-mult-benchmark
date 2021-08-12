package com.dablesr;

import java.util.Random;

public class MatrixUtil {

    public static Matrix generateRandom (int width, int height) {
        Matrix matrix = new Matrix(width, height);
        Random random = new Random();

        for (int r = 0; r < matrix.getNumberOfRows(); r++) {
            for (int c = 0; c < matrix.getNumberOfColumns(); c++) {
                matrix.setValue(r,c, random.nextDouble());
            }
        }

        return matrix;
    }

    public static Matrix generateSparse (int width, int height, double percentage) {
        Matrix matrix = new Matrix(width, height);
        Random random = new Random();

        for (int r = 0; r < matrix.getNumberOfRows(); r++) {
            for (int c = 0; c < matrix.getNumberOfColumns(); c++) {
                if (random.nextDouble() < percentage) {
                    matrix.setValue(r,c, random.nextDouble());
                }
            }
        }

        return matrix;
    }

    public static void prettyPrint (Matrix matrix) {
        for (int r = 0; r < matrix.getNumberOfRows(); r++) {
            System.out.print("|");
            for (int c = 0; c < matrix.getNumberOfColumns(); c++) {
                System.out.print(matrix.getData()[r][c]);
                if (c < matrix.getNumberOfColumns() - 1) {
                    System.out.print(",");
                }
            }
            System.out.print("|");
            System.out.println();
        }
    }
}
