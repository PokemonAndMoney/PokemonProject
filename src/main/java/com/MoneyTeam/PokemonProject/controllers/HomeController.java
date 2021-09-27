package com.MoneyTeam.PokemonProject.controllers;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class HomeController extends BaseController {
	
	@RequestMapping("/") // @RequestParam go/to/my/page?r=1
	public String home() {
		return "index.jsp";
	}
}
