
public class HW06_4111056027_1 extends SortIsAllYouNeed {
    public static void main(String[] args) throws Exception {
        Double A[] = { 1.0, 3.0, 2.0, 5.0, 4.0 };
        Double ans[] = new HW06_4111056027_1().sortWhat(A);
        for (Double d : ans) {
            System.out.print(d + " ");
        }
    }

    @Override
    public Double[] sortWhat(Double[] A) {
        // quick sort
        quickSort(A, 0, A.length - 1);
        return A;
    }

    public void quickSort(Double[] A, int low, int high) {
        if (low < high) {
            int pi = partition(A, low, high);
            quickSort(A, low, pi - 1);
            quickSort(A, pi + 1, high);
        }
    }

    public Double[] swap(Double[] A, int i, int j) {
        Double temp = A[i];
        A[i] = A[j];
        A[j] = temp;
        return A;
    }

    public int partition(Double[] A, int low, int high) {
        Double pivot = A[high];
        int i = low;
        for (int j = low; j < high; j++) {
            if (A[j] < pivot) {
                swap(A, i, j);
                i++;
            }
        }
        swap(A, i, high);
        return i;
    }
}
