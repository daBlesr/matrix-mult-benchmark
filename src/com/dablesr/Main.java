package com.dablesr;

public class Main {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {

            Matrix m1 = MatrixUtil.generateSparse(1000, 1000, 0.05);
            Matrix m2 = MatrixUtil.generateSparse(1000, 1000, 0.05);

            long start = System.currentTimeMillis();
            m1.mult(m2);
            long end = System.currentTimeMillis();
            System.out.println("took " + (end - start) / 1000.0 + " seconds");
        }
    }
}
