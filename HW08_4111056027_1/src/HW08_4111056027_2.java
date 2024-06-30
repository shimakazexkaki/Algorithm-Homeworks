import java.util.Arrays;

public class HW08_4111056027_2 extends LLK {
    public static void main(String[] args) {
        int[][] array = { { 1, 2 }, { 1, 3 }, { 1, 4 }, { 2, 4 } };
        System.out.println(new HW08_4111056027_2().checkLLK(array)); // Expected output: true

        int[][] array2 = { { 0, 0 }, { 1, 0 }, { 1, 1 }, { 0, 1 } };
        System.out.println(new HW08_4111056027_2().checkLLK(array2)); // Expected output: false
    }

    @Override
    public boolean checkLLK(int[][] array) {
        int n = array.length;

        for (int i = 0; i < n; i++) {
            Point[] points = new Point[n - 1];
            int index = 0;

            for (int j = 0; j < n; j++) {
                if (i != j) {
                    points[index++] = new Point(array[j][0] - array[i][0], array[j][1] - array[i][1]);
                }
            }

            Arrays.sort(points, new Comparator<Point>() {
                public int compare(Point p1, Point p2) {
                    return Double.compare(Math.atan2(p1.y, p1.x), Math.atan2(p2.y, p2.x));
                }
            });

            for (int k = 1; k < points.length; k++) {
                if (Math.atan2(points[k].y, points[k].x) == Math.atan2(points[k - 1].y, points[k - 1].x)) {
                    return true;
                }
            }
        }

        return false;
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
