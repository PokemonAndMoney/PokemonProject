package com.MoneyTeam.PokemonProject.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.MoneyTeam.PokemonProject.models.*;
import com.MoneyTeam.PokemonProject.repositories.*;
import java.util.Optional;

@Service
public class PokemonService{
	private final PokemonRepository pokeRepository;
	
	public PokemonService(PokemonRepository pokeRepository) {
		this.pokeRepository = pokeRepository;
	}
	
    public Optional<Pokemon> findPokemonById(Long id) {
    	return this.pokeRepository.findById(id);
    }
}
