package com.dablesr;

public class Main {

    public static void main(String[] args) {
        Matrix m1 = new Matrix(2, 2);
        m1.setValues(
            1, 2,
            4, 3
        );


        Matrix m2 = new Matrix(2, 3);
        m2.setValues(
            1, 2, 3,
            3, -4, 7
        );

        MatrixUtil.prettyPrint(m1.mult(m2));

    }
}
