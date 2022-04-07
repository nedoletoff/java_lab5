import java.util.*;
public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int matrixSize = 5;
        IMatrix aSparse = new SparseMatrix(matrixSize, matrixSize);
        System.out.println(aSparse);
        aSparse.setElement(0, 0, 10);
        System.out.println(aSparse);
        aSparse.setElement(0, 0, 0);
        System.out.println(aSparse);
        aSparse.setElement(0, 4, 2000);
        aSparse.setElement(0, 3, 200);
        aSparse.setElement(0, 2, 20);
        aSparse.setElement(0, 1, 2);
        aSparse.setElement(0, 0, -2);
        System.out.println(aSparse);
        aSparse.setElement(1, 3, 9);
        aSparse.setElement(3, 1, 5);
        aSparse.setElement(2, 2, 7);
        aSparse.setElement(2, 1, 11);
        aSparse.setElement(2, 3, 13);
        aSparse.setElement(4, 0, 3);
        System.out.println(aSparse);
        /*
        IMatrix bSparse = new SparseMatrix(matrixSize, matrixSize);
        for (int i = 0; i < matrixSize; i++) {
            aSparse.setElement(random.nextInt(matrixSize),
                    random.nextInt(matrixSize),
                    random.nextInt(1000) - 500);
        }
        for (int i = 0; i < matrixSize; i++) {
            bSparse.setElement(random.nextInt(matrixSize),
                    random.nextInt(matrixSize),
                    random.nextInt(1000) - 500);
        }
        IMatrix aUsual = new UsualMatrix(aSparse);
        IMatrix bUsual = new UsualMatrix(bSparse);
        System.out.println(aSparse);
        System.out.println(aUsual);
        System.out.println(bSparse);
        System.out.println(bUsual);
        IMatrix resultSparse = aSparse.sum(bSparse);
        IMatrix resultUsual =  aUsual.sum(bUsual);

        System.out.println(resultSparse);
        System.out.println(resultUsual);
         */

    }
}

