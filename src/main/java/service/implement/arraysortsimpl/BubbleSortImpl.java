package service.implement.arraysortsimpl;

import exception.ArrayException;
import service.arraysorts.ArraySort;

public class BubbleSortImpl implements ArraySort {

    @Override
    public void sort(int[] array) throws ArrayException{
        if (array == null) {
            throw new ArrayException("Array cannot be null for sorting");
        }
        if(array.length < 2) {
            return;
        }
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}