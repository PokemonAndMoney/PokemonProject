package com.MoneyTeam.PokemonProject.controllers;
import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import com.MoneyTeam.PokemonProject.repositories.UserRepository;
import com.MoneyTeam.PokemonProject.services.*;

import javax.servlet.http.*;
import javax.validation.Valid;

@SpringBootApplication
@Controller
public class BattleController {
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepo;
	//creating a battle
	@RequestMapping("/battle")
	public String getTeam(HttpSession session,
			User user,
			Model model,
			@ModelAttribute("party") Party party,
			RedirectAttributes redirectAttributes) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId !=  null ) {
			User currentUser = userService.findUserById(userId);
	    	
	    	if(currentUser !=  null) {
	    		List<Party> usersParties = currentUser.getParty();
	    		model.addAttribute("allParties", usersParties);
	    	}
		}
		return "battle.jsp";
	}
	@RequestMapping("/battle/setup/{oppId}")
	public String setupBattle(@PathVariable("oppId") int oppId, HttpSession session, Model model) {
		// this page will display all parties for the user and opponent
		// the user will choose their party and the opponents party to battle and then go to the battle page
		Long userId = (Long) session.getAttribute("userId");
		if(userId !=  null ) {
	    	User currentUser = userService.findUserById(userId);
	    	User opponent = userService.findUserById((long) oppId);
	    	if(currentUser !=  null) {
	    		List<Party> usersParties = currentUser.getParty();
	    		model.addAttribute("currentUserParties", usersParties);
	    	}
	    	if(opponent !=  null) {
	    		List<Party> opponentParties = opponent.getParty();
	    		model.addAttribute("opponentParties", opponentParties);
	    	}
		}
		
		return "battleSetup.jsp";
	}
}
