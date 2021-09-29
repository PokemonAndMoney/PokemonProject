package com.MoneyTeam.PokemonProject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.MoneyTeam.PokemonProject.models.*;

public interface PartyRepository extends CrudRepository<Party, Long>{
	List<Party> findAll();
	Optional<Party> findById(Long id);
}
