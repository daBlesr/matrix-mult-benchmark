package com.dablesr;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Matrix {

    private final int nColumns;
    private final int nRows;
    private final double[][] data;

    public Matrix (int nRows, int nColumns) {
        this.nRows = nRows;
        this.nColumns = nColumns;
        data = new double[nRows][nColumns];
    }

    // returns new matrix with multiplication result
    public Matrix mult (Matrix otherMatrix) {
        Matrix resultMatrix = new Matrix(nRows, otherMatrix.nColumns);

        ExecutorService executor = Executors.newFixedThreadPool(16);

        IntStream.range(0, nRows).forEach(rowIndex -> {
            executor.execute(() -> {
                IntStream.range(0, otherMatrix.nColumns).forEach(otherMatrixColIndex -> {
                    double sum = IntStream.range(0, this.nColumns)
                        .mapToDouble(colIndex -> this.data[rowIndex][colIndex] * otherMatrix.data[colIndex][otherMatrixColIndex])
                        .sum();

                    resultMatrix.setValue(rowIndex, otherMatrixColIndex, sum);
                });
            });
        });

        executor.shutdown();

        try {
            if (executor.awaitTermination(60, TimeUnit.MINUTES)){
                return resultMatrix;
            } else {
                System.out.println("Could not finish matrix multiplication");
            }
        } catch (InterruptedException e) {
            System.out.println("Could not finish matrix multiplication, thread interrupted.");
        }

        return null;
    }

    public int getNumberOfColumns() {
        return nColumns;
    }

    public int getNumberOfRows() {
        return nRows;
    }

    public double[][] getData() {
        return data;
    }

    public void setValue (int rowIndex, int colIndex, double value) {
        this.data[rowIndex][colIndex] = value;
    }

    public void setValues (double ...values) {
        for (int r = 0; r < nRows; r++) {
            for (int c = 0; c < nColumns; c++) {
                this.setValue(r, c, values[r * nColumns + c]);
            }
        }
    }
}
