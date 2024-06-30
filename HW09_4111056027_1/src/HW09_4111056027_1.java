import java.util.ArrayList;

public class HW09_4111056027_1 extends BuyPhone {
    public static void main(String[] args) {
        int[][] phones = { { 1, 1 }, { 2, 4 }, { 2, 10 }, { 5, 4 }, { 4, 8 }, { 5, 5 }, { 8, 4 }, { 10, 2 },
                { 10, 1 } };
        HW09_4111056027_1 instance = new HW09_4111056027_1();
        int[][] goodPhones = instance.bestPhone(phones);

        // Print the output
        for (int[] phone : goodPhones) {
            System.out.println("[" + phone[0] + ", " + phone[1] + "]");
        }
    }

    @Override
    public int[][] bestPhone(int[][] phones) {
        ArrayList<int[]> goodPhonesList = new ArrayList<>();

        for (int i = 0; i < phones.length; i++) {
            boolean isGood = true;
            for (int j = 0; j < phones.length; j++) {
                if (i != j && phones[j][0] >= phones[i][0] && phones[j][1] >= phones[i][1]) {
                    isGood = false;
                    break;
                }
            }
            if (isGood) {
                goodPhonesList.add(phones[i]);
            }
        }

        int[][] goodPhones = new int[goodPhonesList.size()][2];
        for (int i = 0; i < goodPhonesList.size(); i++) {
            goodPhones[i] = goodPhonesList.get(i);
        }

        return goodPhones;
    }
}
