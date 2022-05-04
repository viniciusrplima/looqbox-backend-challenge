package com.looqbox.vinicius.pokeapi.sorting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class QuickSortTest {

    private StringLengthComparator stringLengthComparator;
    private AlphabeticalOrderComparator alphabeticalOrderComparator;
    private QuickSort<String> quickSort;
    private QuickSort<Integer> integerQuickSort;

    @BeforeEach
    public void setUp() {
        quickSort = new QuickSort<>();
        integerQuickSort = new QuickSort<>();
        stringLengthComparator = new StringLengthComparator();
        alphabeticalOrderComparator = new AlphabeticalOrderComparator();
    }

    private void assertIntegerSort(List<Integer> elements, List<Integer> expected) {
        List<Integer> sortedElements = integerQuickSort.sort(elements, (a, b) -> a.compareTo(b));
        Assertions.assertArrayEquals(sortedElements.toArray(new Integer[]{}), expected.toArray(new Integer[]{}));
    }

    @Test
    public void testSort() {
        assertIntegerSort(List.of(3,2,1), List.of(1,2,3));
        assertIntegerSort(List.of(1,2,3), List.of(1,2,3));
        assertIntegerSort(List.of(1,2,3,4), List.of(1,2,3,4));
        assertIntegerSort(List.of(4,3,2,1), List.of(1,2,3,4));
        assertIntegerSort(List.of(1,2,3,2,1), List.of(1,1,2,2,3));
        assertIntegerSort(List.of(5,5,5,5), List.of(5,5,5,5));
        assertIntegerSort(List.of(), List.of());
        assertIntegerSort(List.of(10), List.of(10));
    }

    private void assertStringLengthSort(List<String> elements, List<String> expected) {
        List<String> sortedElements = quickSort.sort(elements, stringLengthComparator);
        Assertions.assertArrayEquals(sortedElements.toArray(new String[]{}), expected.toArray(new String[]{}));
    }

    @Test
    public void testStringLengthSort() {
        assertStringLengthSort(List.of(""), List.of(""));
        assertStringLengthSort(List.of("joao"), List.of("joao"));
        assertStringLengthSort(List.of("maria", "joao"), List.of("joao", "maria"));
        assertStringLengthSort(List.of("a", "bb", "ccc"), List.of("a", "bb", "ccc"));
        assertStringLengthSort(List.of("ccc", "bb", "a"), List.of("a", "bb", "ccc"));
    }

    private void assertAlphabeticalOrderSort(List<String> elements, List<String> expected) {
        List<String> sortedElements = quickSort.sort(elements, alphabeticalOrderComparator);
        Assertions.assertArrayEquals(sortedElements.toArray(new String[]{}), expected.toArray(new String[]{}));
    }

    @Test
    public void testAlphabeticalOrderSort() {
        assertAlphabeticalOrderSort(List.of(""), List.of(""));
        assertAlphabeticalOrderSort(List.of("joao"), List.of("joao"));
        assertAlphabeticalOrderSort(List.of("maria", "joao"), List.of("joao", "maria"));
        assertAlphabeticalOrderSort(List.of("c", "b", "a"), List.of("a", "b", "c"));
        assertAlphabeticalOrderSort(List.of("abc", "ab", "a"), List.of("a", "ab", "abc"));
        assertAlphabeticalOrderSort(List.of("abc", "ab", "a", "aaaa"), List.of("a", "aaaa", "ab", "abc"));
        assertAlphabeticalOrderSort(List.of("aaa", "aaa", "aaa"), List.of("aaa", "aaa", "aaa"));
    }
}
