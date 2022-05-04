package com.looqbox.vinicius.pokeapi.service;

import com.looqbox.vinicius.pokeapi.constants.OrderBy;
import com.looqbox.vinicius.pokeapi.sorting.AlphabeticalOrderComparator;
import com.looqbox.vinicius.pokeapi.sorting.QuickSort;
import com.looqbox.vinicius.pokeapi.sorting.Sort;
import com.looqbox.vinicius.pokeapi.sorting.StringLengthComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PokemonService {

    @Autowired
    private PokeApiService pokeApiService;

    public List<String> listPokemons(String query, String orderBy) {
        List<String> pokemons = pokeApiService.searchPokemons(query);
        Sort<String> sorting = new QuickSort<>();

        if (OrderBy.LENGTH_SORT.equals(orderBy)) {
            pokemons = sorting.sort(pokemons, new StringLengthComparator());
        }
        else if (OrderBy.ALPHABETICAL_SORT.equals(orderBy)) {
            pokemons = sorting.sort(pokemons, new AlphabeticalOrderComparator());
        }

        return pokemons;
    }

}
