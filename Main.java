public class Main {
    public static void main(String[] values) {
        SparseMatrix n = new SparseMatrix(10,10);
        System.out.println(n);
        n.setElement(0, 0, 5);
        n.setElement(0, 2, 3);
        System.out.println(n);
    }
}

