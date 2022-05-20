package com.looqbox.vinicius.pokeapi.mapper;

import com.looqbox.vinicius.pokeapi.dto.HighlightDTO;
import com.looqbox.vinicius.pokeapi.dto.PokemonDTO;

public class PokemonMapper {

    public static PokemonDTO toPokemonDTO(String pokemonName, String query) {
        PokemonDTO pokemon = new PokemonDTO();
        pokemon.setName(pokemonName);

        if (query.length() > 0) {
            HighlightDTO highlight = createHighlight(pokemonName, query);
            pokemon.setHighlight(highlight);
        }

        return pokemon;
    }

    private static HighlightDTO createHighlight(String name, String query) {
        int startIndex = name.indexOf(query);
        int endIndex = startIndex + query.length();
        String formattedName = name.substring(0, startIndex) +
                "<b>" + name.substring(startIndex, endIndex) + "</b>" +
                name.substring(endIndex, name.length());

        HighlightDTO highlight = new HighlightDTO();
        highlight.setStart(startIndex);
        highlight.setEnd(endIndex);
        highlight.setFormattedName(formattedName);

        return highlight;
    }
}
