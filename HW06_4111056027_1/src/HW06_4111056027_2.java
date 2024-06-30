public class HW06_4111056027_2 extends SortIsAllYouNeed {
    public static void main(String[] args) throws Exception {
        Double A[] = { 1.0, 3.0, 2.0, 5.0, 4.0 };
        Double ans[] = new HW06_4111056027_2().sortWhat(A);
        for (Double d : ans) {
            System.out.print(d + " ");
        }
    }

    @Override
    public Double[] sortWhat(Double[] A) {
        // shell sort
        int n = A.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                Double temp = A[i];
                int j;
                for (j = i; j >= gap && A[j - gap] > temp; j -= gap) {
                    A[j] = A[j - gap];
                }
                A[j] = temp;
            }
        }
        return A;
    }
}
