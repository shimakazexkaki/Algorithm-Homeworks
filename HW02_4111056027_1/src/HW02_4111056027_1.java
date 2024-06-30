public class HW02_4111056027_1 extends FourSum {

    public static void main(String[] args) throws Exception {
        int[] A = { -1, 1, 2, -4, 4, 8, -3 };
        HW02_4111056027_1 hw = new HW02_4111056027_1();
        System.out.println(hw.F_sum(A));
    }

    public int F_sum(int[] A) {
        // insert sort
        int ans = 0;
        int n = A.length;
        for (int i = 1; i < n; i++) {
            int key = A[i];
            int j = i - 1;
            while (j >= 0 && A[j] > key) {
                A[j + 1] = A[j];
                j = j - 1;
            }
            A[j + 1] = key;
        }

        // 4sum
        for (int i = 0; i < n; i++) {
            if (i != 0 && A[i] == A[i - 1])
                continue;
            for (int j = i + 1; j < n; j++) {
                if (j != i + 1 && A[j] == A[j - 1])
                    continue;
                int left = j + 1, right = n - 1;
                while (left < right) {
                    int sum = A[i] + A[j] + A[left] + A[right];
                    if (sum == 0) {
                        ans++;
                        left++;
                        right--;
                        while (left < right && A[left] == A[left - 1])
                            left++;
                        while (left < right && A[left] == A[right + 1])
                            right--;
                    } else if (sum < 0) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return ans;
    }
}
