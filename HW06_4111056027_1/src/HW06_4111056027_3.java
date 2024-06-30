import java.util.Arrays;

public class HW06_4111056027_3 extends SortIsAllYouNeed {
    public static void main(String[] args) {
        // This is a placeholder for the main method
        Double[] A = { 1.0, 3.0, 2.0, 5.0, 4.0 };
        Double[] ans = new HW06_4111056027_3().sortWhat(A);
        for (Double d : ans) {
            System.out.print(d + " ");
        }
    }

    @Override
    public Double[] sortWhat(Double[] A) {
        // quick sort
        Arrays.sort(A);
        return A;
    }
}
