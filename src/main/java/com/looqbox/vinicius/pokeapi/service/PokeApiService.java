package com.looqbox.vinicius.pokeapi.service;

import com.looqbox.vinicius.pokeapi.dto.pokeapi.ResultsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PokeApiService {

    @Value("${application.pokeapi.url}")
    private String pokeapiUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Cacheable("pokemons")
    public List<String> getPokemons() {
        System.out.println("hello");
        return restTemplate.getForObject(pokeapiUrl, ResultsDTO.class).getResults().stream()
                .map(pokemon -> pokemon.getName())
                .collect(Collectors.toList());
    }

}
