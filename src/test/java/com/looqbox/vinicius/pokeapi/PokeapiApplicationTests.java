package com.looqbox.vinicius.pokeapi;

import com.looqbox.vinicius.pokeapi.service.PokeApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PokeapiApplicationTests {

	@Autowired
	private PokeApiService pokeApiService;

	@Test
	void testSearchPokemons() {
		List<String> pokemons = pokeApiService.searchPokemons("cha");
		System.out.println(pokemons);
	}

}
