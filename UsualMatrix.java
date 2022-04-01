import java.lang.StringBuilder;

public class UsualMatrix extends Matrix implements IMatrix<UsualMatrix> {
    protected int[][] matrix;


    public UsualMatrix(int rowsNum, int columnsNum) throws MyException {
        if (rowsNum < 0)
            throw new MyException("rowsNum <= 0");
        if (columnsNum < 0)
            throw new MyException("columnsNum <= 0");
        this.rowsNum = rowsNum;
        this.columnsNum = columnsNum;
        this.matrix = new int[rowsNum][columnsNum];
    }

    public UsualMatrix(UsualMatrix other) {
        this.rowsNum = other.rowsNum;
        this.columnsNum = other.columnsNum;
        this.matrix = new int[rowsNum][columnsNum];
        for (int i = 0; i < rowsNum; i++)
            for (int j = 0; j < columnsNum; j++)
                this.setElement(i, j, other.getElement(i, j));
    }

    public void setElement(int row, int column, int value) throws MyException {
        if (row >= this.getRowsNum())
            throw new MyException("row >= rowsNum");
        if (column >= getColumnsNum())
            throw new MyException("column >= columnsNum");
        if (row < 0)
            throw new MyException("row < 0");
        if (column < 0)
            throw new MyException("column < 0");

        this.matrix[row][column] = value;
    }

    public int getElement(int row, int column) throws MyException {
        if (row >= this.getRowsNum())
            throw new MyException("row >= rowsNum");
        if (column >= getColumnsNum())
            throw new MyException("column >= columnsNum");
        if (row < 0)
            throw new MyException("row < 0");
        if (column < 0)
            throw new MyException("column < 0");

        return this.matrix[row][column];
    }
}



        



