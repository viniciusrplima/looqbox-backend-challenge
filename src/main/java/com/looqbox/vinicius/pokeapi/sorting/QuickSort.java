package com.looqbox.vinicius.pokeapi.sorting;

import java.util.Comparator;
import java.util.List;

/* 
    O quicksort é um algoritmo de complexidade O(n log n) 
    que funciona utilizando recursão.

    No geral, ele funciona da seguinte forma: os elementos são particionados
    em duas partes e um pivot, na qual a parte da direita tem os elementos maiores
    que o pivot e a parte esquerda tem os elementos menores que o pivot. No processo
    de particionamento o pivot fica na posição correta dele, ou seja, ele não será 
    mais alterado até o final da ordenação. Em seguida, esse processo é
    feito com as outras duas partes separadamente.
    Isso é feito até que se chegue em uma situação trivial em que temos
    apenas um ou nenhum elemento na parte.

    No particionamento a primeira coisa a ser feita é escolher o pivot (nesse caso, 
    foi escolhido o último elemento por facilidade mas qualquer outro elemento serviria).
    Utilizo um índice para marcar o final da parte da esquerda (leftIndex) e vou iterando
    pelos elementos e testando se são maiores ou menores que o pivot, se são menores
    eu troco a posição dele com o elemento da posição leftIndex (utilizando o swap) e
    incremento o leftIndex. Ao final da iteração eu chego na posição anterior à posição 
    do pivot, ou seja, ele ainda está na última posição. Assim, no último comando eu troco
    ele de lugar com o elemento da posição leftIndex que é o primeiro elemento da parte 
    direita. Dessa forma o pivot fica dividindo as partes. O que estiver do lado esquerdo
    dele é menor que ele e o que estiver do lado direito é maior.
*/
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
