package com.MoneyTeam.PokemonProject.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class HomeController extends BaseController {
	@Autowired
	private RestTemplate restTemplate;
	
	private static String pokemonUrl = "https://pokeapi.co/api/v2/pokemon/";
	
	@RequestMapping("/") // @RequestParam go/to/my/page?r=1
	public String home() {
		return "index.jsp";
	}
	@GetMapping("/{name}")
	public Object getPokemon(@PathVariable("name") String name){
		LinkedHashMap pokemon =  restTemplate.getForObject(pokemonUrl + name, LinkedHashMap.class);
//		return pokemon.get("abilities");
		return pokemon;
	}
	
}
