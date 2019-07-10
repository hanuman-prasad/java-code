package sort;

import common.MergeTwoDifferentPartOfSortedArray;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        MergeSort sort = new MergeSort();

//        int arr[] = {5, 8, 1, 2, 9, 7, 11, 23, 21, 56};
        int arr[] = {2,1,-9};

        sort.mergeSort(arr);

        System.out.println(Arrays.toString(arr));
    }

    public void mergeSort(int[] unsortedArray) {
        if (unsortedArray == null || unsortedArray.length <= 1) {
            return;
        }

        sort(unsortedArray, 0, unsortedArray.length - 1);
    }

    private void sort(int[] array, int startIndex, int endIndex) {


        int mid = (startIndex + endIndex) / 2;

        if (mid > startIndex) {
            sort(array, startIndex, mid);
            sort(array, mid + 1, endIndex);
        }

        MergeTwoDifferentPartOfSortedArray.merge(array, startIndex, mid + 1, endIndex);
    }
}
