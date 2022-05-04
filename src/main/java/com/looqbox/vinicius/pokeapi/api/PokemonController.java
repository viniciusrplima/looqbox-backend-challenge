package com.looqbox.vinicius.pokeapi.api;

import com.looqbox.vinicius.pokeapi.constants.OrderBy;
import com.looqbox.vinicius.pokeapi.dto.ErrorResponseDTO;
import com.looqbox.vinicius.pokeapi.dto.HighlightDTO;
import com.looqbox.vinicius.pokeapi.dto.PokemonDTO;
import com.looqbox.vinicius.pokeapi.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    private static final String INVALID_ORDER_BY_MSG = "Invalid value of parameter orderBy: '%s'. " +
                                                        "Instead, use 'length' or 'alphabetical'";

    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    public ResponseEntity<?> listPokemons(@RequestParam(name = "q", required = false, defaultValue = "") String query,
                                          @RequestParam(name = "orderBy", required = false, defaultValue = "") String orderBy) {

        if (orderBy.length() > 0 &&
            !OrderBy.LENGTH_SORT.equals(orderBy) &&
            !OrderBy.ALPHABETICAL_SORT.equals(orderBy)) {

            HttpStatus status = HttpStatus.BAD_REQUEST;
            String errorMessage = String.format(INVALID_ORDER_BY_MSG, orderBy);

            return ResponseEntity.status(status).body(new ErrorResponseDTO(errorMessage, status.value()));
        }

        List<PokemonDTO> pokemons = pokemonService.listPokemons(query, orderBy).stream()
                .map((pokemonName) -> toPokemonDTO(pokemonName, query))
                .collect(Collectors.toList());

        return ResponseEntity.ok(pokemons);
    }

    private PokemonDTO toPokemonDTO(String pokemonName, String query) {
        PokemonDTO pokemon = new PokemonDTO();
        pokemon.setName(pokemonName);

        if (query.length() > 0) {
            HighlightDTO highlight = createHighlight(pokemonName, query);
            pokemon.setHighlight(highlight);
        }

        return pokemon;
    }

    private HighlightDTO createHighlight(String name, String query) {
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
