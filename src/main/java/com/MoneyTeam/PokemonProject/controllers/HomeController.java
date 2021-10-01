package com.MoneyTeam.PokemonProject.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.MoneyTeam.PokemonProject.classes.APIPoke;
import com.MoneyTeam.PokemonProject.classes.Ability;
import com.MoneyTeam.PokemonProject.classes.Move;
import com.MoneyTeam.PokemonProject.classes.Sprite;
import com.MoneyTeam.PokemonProject.classes.Stat;
import com.MoneyTeam.PokemonProject.models.Party;
import com.MoneyTeam.PokemonProject.models.User;
import com.MoneyTeam.PokemonProject.services.UserService;

@SpringBootApplication
@Controller
public class HomeController extends BaseController {
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserService userService;
	
	private static String pokemonUrl = "https://pokeapi.co/api/v2/pokemon/";
	
	@RequestMapping("/") // @RequestParam go/to/my/page?r=1
	public String home(HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId !=  null ) {
			User currentUser = userService.findUserById(userId);
	    	
	    	if(currentUser !=  null) {
	    		List<Party> usersParties = currentUser.getParty();
	    		model.addAttribute("allParties", usersParties);
	    	}
		}
		return "index.jsp";
	}
	@GetMapping("/{name}")
	public Object getPokemon(@PathVariable("name") String name, Model model){
		Object pokemonObj =  restTemplate.getForObject(pokemonUrl + name, Object.class); // retrieves entire pokemon object	
		
		// api calls 
		APIPoke pokemon =  restTemplate.getForObject(pokemonUrl + name, APIPoke.class);
		
		// abilities
		List<Ability> allAbililties = pokemon.getAbilities(); // returns array of linked hashmaps containing the ability data
		String abilityName = (String) pokemon.getAbilities().get(0).getAbility().getName(); // returns the name of the first ability within the array of linked hashmaps 
		
		// moves 
		List<Move> allMoves = pokemon.getMoves(); // returns array of linked hashmaps containing the moves data
		String move = pokemon.getMoves().get(0).getMove().getName(); // returns the name of the first move within the array of linked hashmaps
		
		// sprites or pokemon image
		Sprite allSprites = pokemon.getSprites(); // returns array of linked hashmaps containing the url's of the pokemon's front and back images
		String pokeSprite = pokemon.getSprites().getBack_default(); // returns url of the pokemon's default back image
		
		// stats
		List<Stat> allStats = pokemon.getStats(); // returns array of linked hashmaps containing data of the pokemons base stats
		int statValue = pokemon.getStats().get(0).getBase_stat(); // returns the base stat value of the first linked hashmap in the array (hp)
		String statName = pokemon.getStats().get(0).getStat().getName(); // returns the stat name of the first linked hashmap in the array (hp)
//		model.addAttribute("test", pokemonObj);
		return pokemonObj;
	}
	
}
