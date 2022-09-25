package banana.pekan.projection;

import java.util.Arrays;

public class MatrixHelper {

    /*

        matrix structure: {
            [1, 0],
            [0, 1],
            [1, 1]
        }

        rows = 3
        columns = 2

        matrix = 3x2

     */

    public static float[][] createMatrix(int rows, int columns) {
        float[][] matrix = new float[rows][columns];

        for (int i = 0; i < rows; i++) {
            Arrays.fill(matrix[i], 0);
        }

        return matrix;

    }

    public static float[][] vecToMatrix(PVector vector) {
        // creating a blank new matrix
        float[][] matrix = createMatrix(vector.dim, 1);

        matrix[0][0] = vector.x;
        matrix[1][0] = vector.y;

        // checking if the vector has a 3rd dimension
        if (vector.hasZ()) {
            matrix[2][0] = vector.z;
        }

        // returning the matrix
        return matrix;
    }

    public static PVector matrixToVec(float[][] matrix) {

        int rows = matrix.length;
        int columns = matrix[0].length;

        // creating a new vector
        PVector vector = new PVector(matrix[0][0]);

        // checking if the matrix has a 'y' component and setting it for the vector
        if (columns > 1) {
            vector.y = matrix[1][0];
        }

        // checking if the matrix has a 'z' component and setting it for the vector.
        if (columns > 2) {
            vector.z = matrix[2][0];
        }

        // returning the vector
        return vector;
    }

    public static float[][] rotateMatrix(float[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        float[][] newMatrix = createMatrix(cols, rows);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                newMatrix[j][i] = matrix[i][j];
            }
        }

        return newMatrix;

    }

    public static float[][] multiply(float[][] a, float[][] b) {

        // rotating the 'b' matrix
        b = rotateMatrix(b);

        // getting the rows and columns
        int rowsA = a.length;
        int rowsB = b.length;
        int colsA = a[0].length;
        int colsB = b[0].length;

        // checking for compatibility
        if (colsA != colsB) {
            throw new Error("Both matrices are not compatible with each other.");
        }

        // creating a new matrix
        float[][] matrix = createMatrix(colsA, rowsA);

        for (int row = 0; row < rowsA; row++) {
            for (int rowB = 0; rowB < rowsB; rowB++) {
                int sum = 0;
                for (int col = 0; col < colsA; col++) {
                    sum += a[row][col] * b[rowB][col];
                }
                matrix[row][rowB] = sum;
            }
        }

        return matrix;

    }

    public static PVector multiply(PVector vec, float[][] matrix) {
        // converting the vector to a matrix
        float[][] vector = vecToMatrix(vec);

        // getting the rows and columns of both matrices
        int vecRows = vector.length;
        int matColumns = matrix[0].length;

        // checking if both of the matrices are compatible with each other to multiply with.
        if (vecRows != matColumns) {
            throw new Error("Matrix and vector are not compatible with each other.");
        }

        return matrixToVec(multiply(matrix, vector));

    }

}
