package sort;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    private static final int RANGE = 70;
    private static final int SIZE = 20;
    private static Random rand = new Random();


    public static void main(String[] args) {
        QuickSort sort = new QuickSort();

        int[] arr = new int[SIZE];

        for (int i = 0; i < SIZE; i++) {
            arr[i] = rand.nextInt(RANGE);
        }

//        Arrays.sort(arr);
//        for (int i = 0; i < SIZE/2; i++) {
//            sort.swap(arr, i, SIZE-i-1);
//        }

        System.out.println(Arrays.toString(arr));

        sort.sort(arr);

        System.out.println();
        System.out.println("---------------------");
        System.out.println(Arrays.toString(arr));

    }


    public void sort(int[] unsortedArray) {
        if (unsortedArray == null || unsortedArray.length <= 1) {
            return;
        }

        sort(unsortedArray, 0, unsortedArray.length - 1);
    }

    private void sort(int[] arr, int low, int high) {
        if (low < high) {
            int partition = partition(arr, low, high);

            sort(arr, low, partition - 1);
            sort(arr, partition + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {

        int pivot = arr[high];
        int i = low;
        int j = low;

        while (j < high) {
            if (arr[j] < pivot) {
                if (i != j) {
                    swap(arr, i, j);
                }
                i++;

            }
            j++;


        }

        swap(arr, i, high);

        return i;
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
