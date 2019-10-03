package ru.motrichkin.datastructures;

import java.util.Arrays;

public class MyArrayListWithSorting<ItemClassComparable extends Comparable> extends MyArrayList {

    protected void swap(int i0, int i1) {
        if (i0 == i1) {
            return;
        }
        ItemClassComparable hat = (ItemClassComparable) array[i0];
        array[i0] = array[i1];
        array[i1] = hat;
    }

    protected boolean less(int i0, int i1) {
        return ((ItemClassComparable) array[i0]).compareTo(array[i1]) < 0;
    }

    public void bubbleSort() {
        boolean atLeastOneSwap = true;
        while (atLeastOneSwap) {
            atLeastOneSwap = false;
            for (int i = firstItemIndex; i < lastItemIndex; i++) {
                if (less(i + 1, i)) {
                    swap(i + 1, i);
                    atLeastOneSwap = true;
                }
            }
        }
    }

    public void insertionSort() {
        for (int i = firstItemIndex + 1; i <= lastItemIndex; i++) {
            int j = i - 1;
            ItemClassComparable item = (ItemClassComparable) get(i);
            while (j >= firstItemIndex && item.compareTo(array[j]) < 0) {
                array[j + 1] = array[j];
                j--;
            }
            j++;
            if (j != i) {
                array[j] = item;
            }
        }
    }

    public void selectionSort() {
        for (int i = firstItemIndex; i < lastItemIndex; i++) {
            int currentIndexOfMinimum = i;
            for (int j = i + 1; j <= lastItemIndex; j++) {
                if (((ItemClassComparable) array[j]).compareTo(array[currentIndexOfMinimum]) < 0) {
                    currentIndexOfMinimum = j;
                }
            }
            swap(i, currentIndexOfMinimum);
        }
    }

    public void qSort() {
        Arrays.sort(array, firstItemIndex, lastItemIndex + 1);
    }

}
