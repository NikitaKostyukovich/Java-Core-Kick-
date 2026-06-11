package service.implement.arraysortsimpl;

import exception.ArrayException;
import service.arraysorts.ArraySort;

public class QuickSortImpl implements ArraySort {
    @Override
    public void sort(int[] array)  throws ArrayException{
        if (array == null)
        {
            throw new ArrayException("Array cannot be null for sorting");
        }
        if(array.length < 2) {
            return;
        }
        quickSort(array, 0, array.length - 1);
    }
    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            // Рекурсивно сортируем части до и после опорного элемента
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }
    private int partition(int[] array, int low, int high) {
        int pivot = array[high]; // Выбираем последний элемент как опорный
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}