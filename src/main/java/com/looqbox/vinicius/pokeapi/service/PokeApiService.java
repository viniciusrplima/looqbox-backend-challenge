package com.looqbox.vinicius.pokeapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PokeApiService {

    @Value("${application.pokeapi.url}")
    private String pokeapiUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public List<String> searchPokemons(String prefix) {
        String queryTemplate = "{\"query\":\"query {pokemon_v2_pokemon(where: {name: {_like: \\\"%s\\\"}}) {name}}\",\"variables\":{}}";
        String query = String.format(queryTemplate, "%" + prefix + "%");

        Map<String, Object> responseObject = restTemplate.postForObject(pokeapiUrl, query, Map.class);
        Map<String, Object> data = (Map<String, Object>) responseObject.get("data");
        List<String> pokemons = ((List<Map<String, String>>) data.get("pokemon_v2_pokemon")).stream()
                .map((pokemonObj) -> pokemonObj.get("name"))
                .collect(Collectors.toList());

        return pokemons;
    }

}
