package common;

public class MergeTwoDifferentPartOfSortedArray {

    public static void merge(int[] arr, int start, int mid, int end) {
        if (start < 0 || mid < start || end >= arr.length) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int len1 = mid - start;
        int len2 = end - mid + 1;
        int[] first = new int[len1];
        int[] second = new int[len2];

        for (int i = 0; i < len1; i++) {
            first[i] = arr[start + i];
        }

        for (int i = 0; i < len2; i++) {
            second[i] = arr[mid + i];
        }

        int i = start;
        int j = 0;
        int k = 0;
        while (j < len1 && k < len2) {
            if (second[k] < first[j]) {
                arr[i++] = second[k++];
            } else {
                arr[i++] = first[j++];
            }

        }

        while (j < len1) {
            arr[i++] = first[j++];
        }
        while (k < len2) {
            arr[i++] = second[k++];
        }


    }
}
