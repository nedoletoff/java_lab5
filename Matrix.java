public abstract class Matrix {
    protected int rowsNum;
    protected int columnsNum;

    public abstract void setElement(int row, int column, int value);

    public abstract int getElement(int row, int column);

    public int getRowsNum() {
        return this.rowsNum;
    }

    public int getColumnsNum() {
        return this.columnsNum;
    }
}



        



