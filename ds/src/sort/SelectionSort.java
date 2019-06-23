package sort;

import java.util.Arrays;

public class SelectionSort {

    public static void main(String[] ss) {
        int[] arr = {3, 1, 7, 3, 8, 23, 1, 9, 5, 6};


        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr) {

		int len = arr.length;
		
		for(int i=0; i<len;i++){
			int min = i;
			
			for(int j=i+1;j<len;j++){
				if(arr[j] < arr[min]){
					min = j;
				}
			}
			
			swap(arr, min, i);
		}   
    }
	
	private static void swap(int[] arr, int i, int j){
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] =t;
	}
}
