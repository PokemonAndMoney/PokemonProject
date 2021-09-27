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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.lang.*;

import com.MoneyTeam.PokemonProject.models.*;
import com.MoneyTeam.PokemonProject.services.*;
import javax.servlet.http.*;


@SpringBootApplication
@Controller
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;

@RequestMapping("/home/login")
public String loginForm(@ModelAttribute("user")User user) {
	return "/login.jsp";
}
@RequestMapping("/home/logout")
public String Logout(HttpSession session,
		User user,
		RedirectAttributes redirectAttributes) {
	if(this.userService.login(user) != null) {
		session.invalidate();
		redirectAttributes.addFlashAttribute("messages", new ArrayList<String>(Arrays.asList("You have successfully logged out.")));
		return "redirect:/";
	}
	session.invalidate();
	redirectAttributes.addFlashAttribute("messages", new ArrayList<String>(Arrays.asList("You have successfully logged out.")));
	return "redirect:/";
}
@PostMapping("/home/login")
public String Login(
		@Valid User user, BindingResult result,
		RedirectAttributes redirectAttributes,
		HttpSession session
		) {
	if(result.hasErrors()) return "/home/login";
	user = this.userService.login(user);
	//either no email found or incorrect password
	if(user == null) {
		redirectAttributes.addFlashAttribute("errors", new ArrayList<String>(Arrays.asList("invalid credentials")));
		return "redirect:/login";
	}
	//creating session for user after successful login
	session.setAttribute("userId", user.getId());
	redirectAttributes.addFlashAttribute("messages", new ArrayList<String>(Arrays.asList("Welcome Back!")));
	return "redirect:/";
}

//checking if user is logged in
private Optional<User> getUserSession(HttpSession session) {
	Long id = (Long)session.getAttribute("userId");
	if(id == null) return null;
	return this.userService.findUserById(id);
}


	
	
@RequestMapping("/home/register")
public String registerForm(@ModelAttribute("user") User user) {
	return "/register.jsp";
}
@PostMapping("home/register")
	public String register(
			@Valid User user,
			BindingResult result, 
			RedirectAttributes redirectAttributes,
			HttpSession session
			) {
	if(result.hasErrors()) return "/home/register";
	if(this.userService.login(user) != null) {
		redirectAttributes.addFlashAttribute("messages", new ArrayList<String>(Arrays.asList(
				//this message is given if the user is already present in database as well.
				"Invalid input. Please make sure your passwords match.")));
		
		return "redirect:/home/register";
	}
	//registration
	user = this.userService.register(user);
	if(user == null) {
		redirectAttributes.addFlashAttribute("errors", new ArrayList<String>(Arrays.asList("invalid credentials")));
		return "redirect:/home/register";
	}
	session.setAttribute("userId", user.getId());
	redirectAttributes.addFlashAttribute("messages", new ArrayList<String>(Arrays.asList("Welcome To the Library!")));

		return "redirect:/";
	}
	

	
}