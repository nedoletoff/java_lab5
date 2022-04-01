import java.lang.StringBuilder;
interface IMatrix<T> {
    void setElement(int row, int column, int value);
    int getElement(int row, int column);
    int getRowsNum();
    int getColumnsNum();
    default T sum(T other) throws MyException {
        if (this.getRowsNum() != other.getRowsNum() || this.getColumnsNum() != other.getColumnsNum())
            throw new MyException("matrix sizes are not the same");

        T result = new T(this);

        for (int i = 0; i < this.getRowsNum(); i++)
            for (int j = 0; j < this.getColumnsNum(); j++) 
                result.setElement(i, j, result.getElement(i, j)  + other.getElement(i, j));

        return result;
    }
    default T product(T other) throws MyException {
        if (this.getColumnsNum() != other.getRowsNum())
            throw new MyException("matrix cannot be product");

        T result = new T(this.getRowsNum(), other.getColumnsNum());

        for (int m = 0; m < result.getRowsNum(); m++)
            for (int k = 0; k < result.getColumnsNum(); k++) {
                result.setElement(m, k, 0);
                for (int n = 0; n < result.getRowsNum(); n++)
                    result.setElement(m, k, result.getElement(m, k) + 
                    this.getElement(m, n) * other.getElement(n, k));
            }
        return result;
    }

    default String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < this.getRowsNum(); i++) {
            for (int j = 0; j < this.getColumnsNum(); j++) {
                builder.append(this.getElement(i, j));
                builder.append(" ");
            }
            builder.append("\n");
        }

        return builder.toString();
    }

        
    default boolean equals(Object other) {
        if (!(other instanceof T)) {
            return false;
        }
        if (this.getRowsNum() != (other.getRowsNum() || this.getColumnsNum() != other.getColumnsNum()))
            return false;
        for (int i = 0; i < this.getRowsNum(); i++)
            for (int j = 0; j < this.getColumnsNum(); j++)
                if (this.getElement(i, j) != other.getElement(i, j))
                    return false;
        return true;
    }
}



