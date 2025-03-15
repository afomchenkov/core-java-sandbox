package Tasks.Arrays;

public class MergeSorted {
    public static void mergeSorted(int[] arr1, int n, int[] arr2, int m) {
        int p1 = n - 1;
        int p2 = m - 1;

        for (int p = n + m - 1; p >= 0; p--) {
            if (p2 < 0) {
                break;
            }

            if (p1 >= 0 && arr1[p1] > arr2[p2]) {
                arr1[p] = arr1[p1--];
            } else {
                arr1[p] = arr2[p2--];
            }
        }
    }

    public static void main(String[] args) {

    }
}
