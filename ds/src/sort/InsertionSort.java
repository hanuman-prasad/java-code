package sort;

import java.util.Arrays;

public class InsertionSort {


    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 1,1,9,9,9,3,1,4,4,4,4,5,6,7,1};

        System.out.println(Arrays.toString(arr));
        sortRecursive(arr);
//        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr) {

        if (arr == null || arr.length <= 1) {
            return;
        }

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }

            arr[j + 1] = key;
        }
    }

    private static void sortRecursive(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        sortRecursive(arr, 1, 0);
    }


    private static void sortRecursive(int arr[], int i, int j) {
        if (i < arr.length) {
            int key = arr[i];
            j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }

            arr[j + 1] = key;

            sortRecursive(arr, i + 1, i);
        }
    }

}
