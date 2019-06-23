package sort;

import java.util.Arrays;

public class RecursiveBubble {
	
	public static void main(String[] ss) {
        int[] arr = {3, 1, 7, 3, 8, 2, 9, 5, 6};


        System.out.println(Arrays.toString(arr));
        sort(arr,0,0);
        System.out.println(Arrays.toString(arr));
    }
	
	private static void sort(int[] arr, int i, int j){
		int len =  arr.length;
		
		if(i < len-1){
			if(j<len-i-1){
				if(arr[j]>arr[j+1]){
					swap(arr, j, j+1);
				}
				sort(arr, i,j+1);
			}
			sort(arr, i+1, 0);
		}
	}
	
	private static void swap(int[] arr, int i, int j){
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}	
}
