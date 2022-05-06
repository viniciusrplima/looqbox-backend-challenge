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

    public List<String> searchPokemons(String nameSubstr) {
        String graphqlTemplate = "" +
                "query {" +
                "   pokemon_v2_pokemon(where: {name: {_like: \"%s\"}}) {" +
                "       name" +
                "   }" +
                "}";
        String query = createGraphQLQueryObj(graphqlTemplate, "%" + nameSubstr + "%");

        Map<String, Object> responseObject = restTemplate.postForObject(pokeapiUrl, query, Map.class);
        Map<String, Object> data = (Map<String, Object>) responseObject.get("data");
        List<String> pokemons = ((List<Map<String, String>>) data.get("pokemon_v2_pokemon")).stream()
                .map((pokemonObj) -> pokemonObj.get("name"))
                .collect(Collectors.toList());

        return pokemons;
    }

    private String createGraphQLQueryObj(String query, String... variables) {
        String queryTemplate = "" +
                "{" +
                "   \"query\": \"%s\" " +
                "}";
        String formattedQuery = String.format(query, variables);
        String scapedQuery = formattedQuery.replace("\"", "\\\"");
        return String.format(queryTemplate, scapedQuery);
    }

}
