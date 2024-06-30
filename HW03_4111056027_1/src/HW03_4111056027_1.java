public class HW03_4111056027_1 extends DogeCoin {
    public static void main(String[] args) throws Exception {

        int[] A = { 7, 1, 5, 3, 6 };
        System.out.println(new HW03_4111056027_1().doge(A));

    }

    @Override
    public int doge(int[] price) {
        int buy = price[0];
        int profit = 0;
        for (int i = 1; i < price.length; i++) {
            if (price[i] < buy) {
                buy = price[i];
            } else if (price[i] - buy > profit) {
                profit = price[i] - buy;
            }
        }
        return profit;
    }
}
