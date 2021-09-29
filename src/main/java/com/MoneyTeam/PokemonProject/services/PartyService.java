package com.MoneyTeam.PokemonProject.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.MoneyTeam.PokemonProject.models.*;
import com.MoneyTeam.PokemonProject.repositories.*;
import java.util.Optional;

@Service
public class PartyService{
	private final PartyRepository partyRepository;
	
	public PartyService(PartyRepository partyRepository) {
		this.partyRepository = partyRepository;
	}
	
    public Optional<Party> findPartyById(Long id) {
    	return partyRepository.findById(id);
    }
}
