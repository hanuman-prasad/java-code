package sort;

import common.RandomStringGenerator;

import java.util.Arrays;

public class Heap<T extends Comparable> {

    private T[] heapTree;

    public static void main(String[] str) {
        RandomStringGenerator stringGenerator = new RandomStringGenerator();
        Heap<String> heap = new Heap();
        String[] arr = stringGenerator.getArrayOfRandomString(5);

        System.out.println(Arrays.toString(arr));
        heap.buildHeap(arr);
        System.out.println(Arrays.toString(heap.heapTree));
    }

    public void buildHeap(T[] data) {
        heapTree = (T[])new Comparable[data.length];
        System.arraycopy(data, 0, heapTree, 0, data.length);

        for (int i = (heapTree.length / 2) - 1; i >= 0; i--) {
            heapify(i);
        }
    }

    private void heapify(int i) {
        int maxIndex = heapTree.length;
        int lChildIndex = 2 * i + 1;
        int rChildIndex = 2 * i + 2;

        if (lChildIndex < maxIndex && rChildIndex < maxIndex) {
            int swapIndex = heapTree[lChildIndex].compareTo(heapTree[rChildIndex]) > 0 ? lChildIndex : rChildIndex;
            if (heapTree[i].compareTo(heapTree[swapIndex]) < 0) {
                swap(i, swapIndex);
                heapify(swapIndex);
            }
        } else if (lChildIndex < maxIndex && heapTree[i].compareTo(heapTree[lChildIndex]) < 0 ) {
            swap(i, lChildIndex);
            heapify(lChildIndex);
        } else if (rChildIndex < maxIndex && heapTree[i].compareTo(heapTree[rChildIndex]) < 0) {
            swap(i, rChildIndex);
            heapify(rChildIndex);
        }
    }


    private void swap(int i, int j) {
        T t = heapTree[i];
        heapTree[i] = heapTree[j];
        heapTree[j] = t;
    }

}
