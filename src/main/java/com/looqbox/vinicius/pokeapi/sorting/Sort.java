package com.looqbox.vinicius.pokeapi.sorting;

import java.util.Comparator;
import java.util.List;

public interface Sort<T> {

    public List<T> sort(List<T> elements, Comparator<T> comparator);

}
