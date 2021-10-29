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
	
	@Autowired
	private PartyService partyService;
	@Autowired
	private PartyRepository partyRepo;
	
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
	@RequestMapping("/battle/selectUser")
	public String selectUserToBattle(HttpSession session,
	User user,
	Model model,
	@ModelAttribute("party") Party party,
	RedirectAttributes redirectAttributes) {

	List<User> allUsers = userRepo.findAll();
	model.addAttribute("users", allUsers);

	return "selectUserToBattle.jsp";
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
	 return "battle.jsp";
	 }
	 @RequestMapping("/battle/setup/start")
		public String BattleStart(
		HttpSession session,
		User user,
		Model model,
		@ModelAttribute("party") Party party,
		RedirectAttributes redirectAttributes) {

		List<User> allUsers = userRepo.findAll();
		model.addAttribute("users", allUsers);

		return "fight.jsp";
		}
	 @RequestMapping(value="/goToBattle", method=RequestMethod.POST)
		public String process(@RequestParam(value="userId") int userId, @RequestParam(value="oppId") int oppId, HttpSession session, Model model) {
		 
		  Party userParty = partyService.findPartyById((long) userId);
		  Party oppParty = partyService.findPartyById((long) oppId);
		  
		  String oppUserName = oppParty.getUser().getEmail();
		  
		  List<Pokemon> userPokemonTeam = userParty.getPokemon();
		  List<Pokemon> oppPokemonTeam = oppParty.getPokemon();
		  
		  session.setAttribute("userPokemonTeam", userPokemonTeam);
		  session.setAttribute("oppPokemonTeam", oppPokemonTeam);
		  
		  Pokemon userPokemon = userPokemonTeam.get(0); 
		  Pokemon oppPokemon = oppPokemonTeam.get(0);
		  session.setAttribute("userPokemonHp", userPokemon.getHp());
		  session.setAttribute("oppPokemonHp", oppPokemon.getHp());
		  int oppPokemonHp = (int) session.getAttribute("oppPokemonHp");
		  
		  model.addAttribute("userPokemonTeam", userPokemonTeam);
		  model.addAttribute("oppPokemonTeam", oppPokemonTeam);
		  model.addAttribute("oppUsername", oppUserName);
		  model.addAttribute("oppPokemon", oppPokemon);
		  model.addAttribute("oppPokemonHp", oppPokemonHp);
		  model.addAttribute("userPokemon", userPokemon);
		  model.addAttribute("userPokemonHp", session.getAttribute("userPokemonHp"));
		 return "battlePage.jsp";
	 }
	 @RequestMapping(value="/nextPokemon", method=RequestMethod.POST)
		public String nextPokemon( HttpSession session, Model model) {
		 
		 return "string";
	 }
	 @RequestMapping(value="/attack", method=RequestMethod.POST)
		public String attack(@RequestParam(value="userMove") String userMove,
							HttpSession session, Model model) {
		  List<Pokemon> userPokemonTeam = (List<Pokemon>) session.getAttribute("userPokemonTeam");
		  List<Pokemon> oppPokemonTeam = (List<Pokemon>) session.getAttribute("oppPokemonTeam");
		  
		  Pokemon oppPokemon = oppPokemonTeam.get(0);
		  Pokemon userPokemon = userPokemonTeam.get(0);
		  model.addAttribute("userPokemon", userPokemon);
		  model.addAttribute("oppPokemon", oppPokemon);
		  model.addAttribute("oppUsername", oppPokemon.getParty().getUser().getEmail());
		  List<String> oppMoves = new ArrayList<String>
								  (Arrays.asList(
										  oppPokemon.getMove1(),
										  oppPokemon.getMove2(),
										  oppPokemon.getMove3(),
										  oppPokemon.getMove4()));
		  Random rand = new Random();
		  String randMove = oppMoves.get(rand.nextInt(4));
		  
		  int userPokemonHp = (int) session.getAttribute("userPokemonHp");
		  int oppPokemonHp = (int) session.getAttribute("oppPokemonHp");
		  String str1 = "";
		  String str2 = "";

		  if(userPokemon.getSpd() > oppPokemon.getSpd()) {
			  str1 += userPokemon.getName() + " used " + userMove + ".";
			  str2 += oppPokemon.getName() + " used " + randMove + ".";
			  oppPokemonHp -= 30;
			  if (oppPokemonHp <=  0) {
				  if(oppPokemonTeam.size() ==  1) {
					  return "redirect:/winner";
				  }
				  oppPokemonTeam.remove(0);
				  session.setAttribute("oppPokemonTeam", oppPokemonTeam);
				  oppPokemon = oppPokemonTeam.get(0);
				  oppPokemonHp = oppPokemon.getHp();
				  model.addAttribute("oppPokemon", oppPokemon);
				  model.addAttribute("oppPokemonHp", oppPokemonHp);
			  }
			  userPokemonHp -= 20;
			  if(userPokemonHp <=  0) {
				  if(userPokemonTeam.size() ==  1) {
					  return "redirect:/loser";
				  }
				  userPokemonTeam.remove(0);
				  session.setAttribute("userPokemonTeam", userPokemonTeam);
				  userPokemon = userPokemonTeam.get(0);
				  userPokemonHp = userPokemon.getHp();
				  model.addAttribute("userPokemonHp", userPokemonHp);
				  model.addAttribute("userPokemon", userPokemon);
			  }
			  session.setAttribute("userPokemonHp", userPokemonHp);
			  session.setAttribute("oppPokemonHp", oppPokemonHp);
		  } else if (oppPokemon.getSpd() > userPokemon.getSpd()) {
			  str2 += userPokemon.getName() + " used " + userMove + ".";
			  str1 += oppPokemon.getName() + " used " + randMove + ".";
			  userPokemonHp -= 30;
			  if(userPokemonHp <=  0) {
				  if(userPokemonTeam.size() ==  1) {
					  return "loser.jsp";
				  }
				  userPokemonTeam.remove(0);
				  session.setAttribute("userPokemonTeam", userPokemonTeam);
				  userPokemon = userPokemonTeam.get(0);
				  userPokemonHp = userPokemon.getHp();
				  model.addAttribute("userPokemonHp", userPokemonHp);
				  model.addAttribute("userPokemon", userPokemon);
			  }
			  oppPokemonHp -= 20;
			  if (oppPokemonHp <=  0) {
				  if(oppPokemonTeam.size() ==  1) {
					  return "winner.jsp";
				  }
				  oppPokemonTeam.remove(0);
				  session.setAttribute("oppPokemonTeam", oppPokemonTeam);
				  oppPokemon = oppPokemonTeam.get(0);
				  oppPokemonHp = oppPokemon.getHp();
				  model.addAttribute("oppPokemon", oppPokemon);
				  model.addAttribute("oppPokemonHp", oppPokemonHp);
			  }
			  session.setAttribute("userPokemonHp", userPokemonHp);
			  session.setAttribute("oppPokemonHp", oppPokemonHp);
		  }
		  model.addAttribute("userPokemonHp", session.getAttribute("userPokemonHp"));
		  model.addAttribute("oppPokemonHp", session.getAttribute("oppPokemonHp"));
		  model.addAttribute("str1", str1);
		  model.addAttribute("str2", str2);
		 
		 return "battlePage.jsp";
	 }

}
