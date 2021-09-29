package com.MoneyTeam.PokemonProject.controllers;
import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.MoneyTeam.PokemonProject.repositories.PartyRepository;
import com.MoneyTeam.PokemonProject.services.*;
import javax.servlet.http.*;


@SpringBootApplication
@Controller
public class PartyController extends BaseController{
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private UserService userService;
	@Autowired
	private PartyService partyService;
	@Autowired
	private PartyRepository partyRepo;
	@Autowired
	private PokemonService pokeService;
	private static String pokemonUrl = "https://pokeapi.co/api/v2/pokemon/";
	
	//creating a new party for the user
	@RequestMapping("/party/add")
	public String getParty(HttpSession session,
			User user,
			@ModelAttribute("pokemon") Pokemon pokemon,
			String ability,
			String name,
			List<pokeMove> move,
			Long id,
			RedirectAttributes redirectAttributes) {
		return "add_party.jsp";
	}
	@PostMapping("/party/add") // @RequestParam go/to/my/page?r=1
	public String addtoParty(HttpSession session,
			User user,
			@ModelAttribute("pokemon") Pokemon pokemon,
			String ability,
			String name,
			List<pokeMove> move,
			Long id,
			RedirectAttributes redirectAttributes) {
		//redirect to createParty route if user has no parties
		if(partyRepo.findAll() == null) {
			return "redirect:/party/new";
		}
		//create a new party for the user
		pokemon = this.pokeService.Create(pokemon);
		pokemon.setAbility(ability);
		pokemon.setId(id);
		pokemon.setMoves(move);
		
		return "/index.jsp";
	}
	//creating a new party for the user
	@RequestMapping("/party/new")
	public String newParty(HttpSession session,
			User user,
			String name,
			Party party,
			RedirectAttributes redirectAttributes) {
		//making a new party
		
		return "/new_party.jsp";
	}
	@PostMapping("/party/new") 
	public String postParty(HttpSession session,
			User user,
			String name,
			Party party,
			RedirectAttributes redirectAttributes) {
		//making a new party
		party = this.partyService.Create(party);
		party.setName(name);
		party.setUser(user);
		return "/index.jsp";
	}
	//viewing user parties
	@RequestMapping("/party/view") // @RequestParam go/to/my/page?r=1
	public String viewParty(HttpSession session,
			User user,
			String name,
			Party party,
			RedirectAttributes redirectAttributes) {
		//checking user parties
		var list = partyRepo.findAll();
		return "/index.jsp";
	}

	//checking if user is logged in
	private Optional<User> getUserSession(HttpSession session) {
		Long id = (Long)session.getAttribute("userId");
		if(id == null) return null;
		return this.userService.findUserById(id);
	}
}
