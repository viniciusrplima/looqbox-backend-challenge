package com.looqbox.vinicius.pokeapi.sorting;

import java.util.Comparator;

public class StringLengthComparator implements Comparator<String> {

    @Override
    public int compare(String str1, String str2) {
        return str1.length() - str2.length();
    }
}
