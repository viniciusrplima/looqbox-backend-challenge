package com.looqbox.vinicius.pokeapi.dto.pokeapi;

import com.looqbox.vinicius.pokeapi.dto.response.PokemonDTO;

import java.util.List;

public class ResultsDTO {

    private List<PokemonDTO> results;

    public List<PokemonDTO> getResults() {
        return results;
    }
}
