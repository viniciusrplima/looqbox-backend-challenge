package com.looqbox.vinicius.pokeapi.controller;

import com.looqbox.vinicius.pokeapi.constants.OrderBy;
import com.looqbox.vinicius.pokeapi.dto.response.ErrorResponseDTO;
import com.looqbox.vinicius.pokeapi.dto.response.PokemonDTO;
import com.looqbox.vinicius.pokeapi.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.looqbox.vinicius.pokeapi.mapper.PokemonMapper.toPokemonDTO;


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
            !OrderBy.LENGTH.name().equalsIgnoreCase(orderBy) &&
            !OrderBy.ALPHABETICAL.name().equalsIgnoreCase(orderBy)) {

            HttpStatus status = HttpStatus.BAD_REQUEST;
            String errorMessage = String.format(INVALID_ORDER_BY_MSG, orderBy);

            return ResponseEntity.status(status).body(new ErrorResponseDTO(errorMessage, status.value()));
        }

        List<PokemonDTO> pokemons = pokemonService.listPokemons(query, orderBy).stream()
                .map((pokemonName) -> toPokemonDTO(pokemonName, query))
                .collect(Collectors.toList());

        return ResponseEntity.ok(pokemons);
    }

}
