package com.looqbox.vinicius.pokeapi.sorting;

import java.util.Comparator;
import java.util.List;

public class QuickSort<T> implements Sort<T> {

    @Override
    public List<T> sort(List<T> elements, Comparator<T> comparator) {
        T[] elementsArray = elements.toArray((T[]) new Object[]{});
        sort(elementsArray, comparator, 0, elements.size()-1);
        return List.of(elementsArray);
    }

    private void sort(T[] elements, Comparator<T> comparator, int start, int end) {
        if (start < end) {
            int middle = partition(elements, comparator, start, end);

            sort(elements, comparator, start, middle-1);
            sort(elements, comparator, middle+1, end);
        }
    }

    private int partition(T[] elements, Comparator<T> comparator, int start, int end) {
        T target = elements[end];
        int leftIndex = start;

        for (int i = start; i < end; i++) {
            if (comparator.compare(elements[i], target) < 0) {
                swap(elements, leftIndex, i);
                leftIndex++;
            }
        }

        swap(elements, leftIndex, end);
        return leftIndex;
    }

    private void swap(T[] elements, int index1, int index2) {
        T temp = elements[index1];
        elements[index1] = elements[index2];
        elements[index2] = temp;
    }
}
