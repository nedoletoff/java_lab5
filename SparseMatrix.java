import java.lang.StringBuilder;
import java.util.*;

public class SparseMatrix implements IMatrix<SparseMatrix> {
    protected int rowsNum;
    protected int columnsNum;

    public class Element {
        int rowIndex;
        int columnIndex;
        int value;

        Element(int rowIndex, int columnIndex, int value) {
            this.rowIndex = rowIndex;
            this.columnIndex = columnIndex;
            this.value = value;
        }
    }

    protected LinkedList<Element> matrix;

    public SparseMatrix(int rowsNum, int columnsNum) throws MyException {
        if (rowsNum <= 0)
            throw new MyException("rowsNum <= 0");
        if (columnsNum <= 0)
            throw new MyException("columnsNum <= 0");

        this.rowsNum = rowsNum;
        this.columnsNum = columnsNum;
        this.matrix = new LinkedList<Element>();
    }

    public SparseMatrix(SparseMatrix other) {
        this.rowsNum = other.rowsNum;
        this.columnsNum = other.columnsNum;
        this.matrix = new LinkedList<Element>(other.matrix);
    }

    public int getRowsNum() {
        return rowsNum;
    }

    public int getColumnsNum() {
        return columnsNum;
    }

    public void setElement(int row, int column, int value) throws MyException {
        if (this.rowsNum <= row)
            throw new MyException("rowsNum <= rows");
        if (this.columnsNum <= column)
            throw new MyException("columnsNum <= column");
        if (rowsNum < 0)
            throw new MyException("rowsNum < 0");
        if (columnsNum < 0)
            throw new MyException("columnsNum < 0");

        if (value != 0)
            this.addElement(row, column, value);
        else
            this.removeElement(row, column);
    }

    public int getElement(int row, int column) throws MyException {
        ListIterator<Element> it = this.matrix.listIterator();
        boolean rowCheck = false;
        boolean columnCheck = false;

        while (it.hasNext() && it.next().rowIndex < row) {
            it.previous();
            rowCheck = true;
        }
        if (rowCheck) {
            while(it.hasNext() && it.next().columnIndex <= column) {
                it.previous();
                if (it.next().columnIndex == column) {
                    it.previous();
                    columnCheck = true;
                }
            }
        }
        if (rowCheck && columnCheck)
            return it.next().value;
        else 
            return 0;
    }

    private void addElement(int row, int column, int value) {
        ListIterator<Element> it = this.matrix.listIterator();
        boolean rowCheck = false;
        boolean columnCheck = false;

        while (it.hasNext() && it.next().rowIndex < row) {
            it.previous();
            rowCheck = true;
        }
        if (rowCheck) {
            while(it.hasNext() && it.next().columnIndex <= column) {
                it.previous();
                if (it.next().columnIndex == column) {
                    it.previous();
                    columnCheck = true;
                }
            }
        }
        if (rowCheck && columnCheck)
            it.next().value = value;
        else {
            it.add(new Element(row, column, value));
        }
    }

    private void removeElement(int row, int column) {
        ListIterator<Element> it = this.matrix.listIterator();
        boolean rowCheck = false;
        boolean columnCheck = false;

        while(it.hasNext() && it.next().rowIndex < row) {
            it.previous();
            rowCheck = true;
        }
        if (rowCheck) {
            while(it.hasNext() && it.next().columnIndex <= column) {
                it.previous();
                if (it.next().columnIndex == column) {
                    it.previous();
                    columnCheck = true;
                }
            }
        }
        if (rowCheck && columnCheck)
            it.remove();
    }
}

