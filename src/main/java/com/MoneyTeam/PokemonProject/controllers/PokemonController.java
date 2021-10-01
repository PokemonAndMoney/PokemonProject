package com.MoneyTeam.PokemonProject.controllers;

import java.util.*;

import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.lang.*;

import com.MoneyTeam.PokemonProject.classes.*;
import com.MoneyTeam.PokemonProject.models.*;
import com.MoneyTeam.PokemonProject.repositories.UserRepository;
import com.MoneyTeam.PokemonProject.services.*;
import javax.servlet.http.*;


@SpringBootApplication
@Controller
public class PokemonController extends BaseController {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private UserService userService;
//	@Autowired
//	private UserRepository userRepo;
	private static String pokemonUrl = "https://pokeapi.co/api/v2/pokemon/";
	@RequestMapping("/view/{name}")
	public String registerForm(@PathVariable("name") String name,
			Model model, @ModelAttribute("addPokemon") Pokemon addPokemon,
			HttpSession session) {
		
		Object pokemonObj =  restTemplate.getForObject(pokemonUrl + name, Object.class); // retrieves entire pokemon object	
		
		// api calls 
		APIPoke pokemon =  restTemplate.getForObject(pokemonUrl + name, APIPoke.class);
		
		// abilities
		List<Ability> allAbilities = pokemon.getAbilities(); // returns array of linked hashmaps containing the ability data
		String abilityName = (String) pokemon.getAbilities().get(0).getAbility().getName(); // returns the name of the first ability within the array of linked hashmaps 
		
		// moves 
		List<Move> allMoves = pokemon.getMoves(); // returns array of linked hashmaps containing the moves data
		String move = pokemon.getMoves().get(0).getMove().getName(); // returns the name of the first move within the array of linked hashmaps
		
		// sprites or pokemon image
		Sprite allSprites = pokemon.getSprites(); // returns array of linked hashmaps containing the url's of the pokemon's front and back images
		String pokeSprite = pokemon.getSprites().getBack_default(); // returns url of the pokemon's default back image
		String frontSprite = pokemon.getSprites().getFront_default();
		// stats
		List<Stat> allStats = pokemon.getStats(); // returns array of linked hashmaps containing data of the pokemons base stats
		int statValue = pokemon.getStats().get(0).getBase_stat(); // returns the base stat value of the first linked hashmap in the array (hp)
//		String statName = pokemon.getStats().get(0).getStat().getName(); // returns the stat name of the first linked hashmap in the array (hp)
		// name
		String pokemon_name = pokemon.getName();
		// type
		List<Type> allTypes = pokemon.getTypes();
//		String typeName = pokemon.getTypes().get(0).getType().getName(); // returns the stat name of the first linked hashmap in the array (hp)
		
		Long userId = (Long) session.getAttribute("userId");
		if(userId !=  null ) {
			User currentUser = userService.findUserById(userId);
	    	
	    	if(currentUser !=  null) {
	    		List<Party> usersParties = currentUser.getParty();
	    		model.addAttribute("allParties", usersParties);
	    	}
		}
		//adding stuff to model
		model.addAttribute("sprite", allSprites);
		model.addAttribute("stats", allStats);
		model.addAttribute("name", pokemon_name);

		model.addAttribute("moves", allMoves);
		model.addAttribute("abilities", allAbilities);
		model.addAttribute("types", allTypes);
		model.addAttribute("id", pokemon.getId());
		//pokemon id
		System.out.println(pokemon.getId());
		return "/pokemon.jsp";
	}
}