
public class HW04_4111056027_2 extends LanguageModel {
    public static void main(String[] args) throws Exception {

    }

    @Override
    public String nextPredictToken(String[] A) {

        String[] words = A[1].split(" ");
        int candidates = 0;
        String[] word_candidate = new String[10000];
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(A[0])) {
                ++i;
                word_candidate[candidates] = words[i];
                candidates++;
            }

        }

        String maxString = "";
        int max = 0;
        // find the most frequent word in word_candidate, and skip words that has been
        // tested
        for (int i = 0; i < candidates; i++) {
            int count = 0;
            for (int j = 0; j < candidates; j++) {
                if (word_candidate[i].equals(word_candidate[j])) {
                    count++;
                }
            }
            if (count > max) {
                max = count;
                maxString = word_candidate[i];
            }
        }
        return maxString;
    }
}
