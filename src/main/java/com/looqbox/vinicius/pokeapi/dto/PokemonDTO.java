package com.looqbox.vinicius.pokeapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PokemonDTO {

    private String name;

    private HighlightDTO highlight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HighlightDTO getHighlight() {
        return highlight;
    }

    public void setHighlight(HighlightDTO highlight) {
        this.highlight = highlight;
    }
}
