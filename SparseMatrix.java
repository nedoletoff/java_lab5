import java.util.*;

public class SparseMatrix extends Matrix implements IMatrix {
    protected int rowsNum;
    protected int columnsNum;

    public static class Rows {
        int rowIndex;
        public static class Element {
            int columnIndex;
            int value;

            Element(int columnIndex, int value) {
                this.columnIndex = columnIndex;
                this.value = value;
            }
        }
        LinkedList<Element> row;

        Rows(int rowIndex, LinkedList<Element> row) {
            this.rowIndex = rowIndex;
            this.row = new LinkedList<Element>();
        }
        Rows(int rowIndex, int columnIndex, int value) {
            this.rowIndex = rowIndex;
            Element el = new Element(columnIndex, value);
            this.row = new LinkedList<Element>();
            this.row.addLast(el);
        }
    }

    protected LinkedList<Rows> matrix;

    public SparseMatrix(int rowsNum, int columnsNum) throws MyException {
        if (rowsNum <= 0)
            throw new MyException("rowsNum <= 0");
        if (columnsNum <= 0)
            throw new MyException("columnsNum <= 0");

        this.rowsNum = rowsNum;
        this.columnsNum = columnsNum;
        this.matrix = new LinkedList<Rows>();
    }

    public SparseMatrix(SparseMatrix other) {
        this.rowsNum = other.rowsNum;
        this.columnsNum = other.columnsNum;
        this.matrix = new LinkedList<Rows>(other.matrix);
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
        ListIterator<Rows> itRows = this.matrix.listIterator();
        boolean rowCheck = false;

        while (itRows.hasNext() && itRows.next().rowIndex <= row) {
            itRows.previous();
            if (itRows.next().rowIndex == row)
                rowCheck = true;
        }
        if (rowCheck) {
            itRows.previous();
            ListIterator<Rows.Element> itElement = itRows.next().row.listIterator();
            while(itElement.hasNext() && itElement.next().columnIndex <= column) {
                itElement.previous();
                if (itElement.next().columnIndex == column) {
                    itElement.previous();
                    return itElement.next().value;
                }
            }
        }
        return 0;
    }

    public IMatrix sum(IMatrix other) throws MyException {
        if (this.getRowsNum() != other.getRowsNum() || this.getColumnsNum() != other.getColumnsNum())
            throw new MyException("matrix sizes are not the same");

        IMatrix result = new SparseMatrix(this.getRowsNum(), this.getColumnsNum());

        for (int i = 0; i < this.getRowsNum(); i++)
            for (int j = 0; j < this.getColumnsNum(); j++)
                result.setElement(i, j, this.getElement(i, j) + other.getElement(i, j));

        return result;
    }

    public IMatrix product(IMatrix other) throws MyException {
        if(this.getColumnsNum() !=other.getRowsNum())
            throw new MyException("matrix cannot be product");
        IMatrix result = new SparseMatrix(this.getRowsNum(), other.getColumnsNum());

        for(int m = 0; m<result.getRowsNum();m++)
            for(int k = 0; k<result.getColumnsNum();k++)
            {
                result.setElement(m, k, 0);
                for (int n = 0; n < result.getRowsNum(); n++)
                    result.setElement(m, k, result.getElement(m, k) +
                            this.getElement(m, n) * other.getElement(n, k));
            }
        return result;
    }

    private void addElement(int row, int column, int value) {
        ListIterator<Rows> itRows = this.matrix.listIterator();
        boolean rowCheck = false;

        while(itRows.hasNext() && itRows.next().rowIndex <= row) {
            itRows.previous();
            if (itRows.next().rowIndex == row) {
                rowCheck = true;
                break;
            }
        }
        if (rowCheck) {
            itRows.previous();
            ListIterator<Rows.Element> itElement = itRows.next().row.listIterator();
            while(itElement.hasNext() && itElement.next().columnIndex <= column) {
                itElement.previous();
                if (itElement.next().columnIndex == column) {
                    itElement.previous();
                    itElement.next().value = value;
                    break;
                }
            }
        }
        if (rowCheck && itElement.hasPrev()) {
            itElement.previous();
            itElement.add(new Element(column, value));
        }
        else if (rowCheck) {
            itRows.previous();
            itRows.next().rows.addLast(new Rows.Element(column, value));
        }
        else if (this.matrix.size() == 0) {
            matrix.addLast(new Rows(row, column, value));
        }
        else {
            itRows.previous();
            itRows.add(new Rows.Element(column, value));
        }
    }

    private void removeElement(int row, int column) {
        ListIterator<Rows> itRows = this.matrix.listIterator();
        boolean rowCheck = false;

        while(itRows.hasNext() && itRows.next().rowIndex <= row) {
            itRows.previous();
            if (itRows.next().rowIndex == row) {
                rowCheck = true;
                break;
            }
        }
        if (rowCheck) {
            itRows.previous();
            ListIterator<Rows.Element> itElement = itRows.next().row.listIterator();
            while(itElement.hasNext() && itElement.next().columnIndex <= column) {
                itElement.previous();
                if (itElement.next().columnIndex == column) {
                    itElement.previous();
                    itElement.remove();
                    break;
                }
            }
        }
    }
}

