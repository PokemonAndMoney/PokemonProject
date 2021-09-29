package com.MoneyTeam.PokemonProject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.MoneyTeam.PokemonProject.models.*;

public interface PokemonRepository extends CrudRepository<Pokemon, Long>{
	List<Pokemon> findAll();
	Optional<Pokemon> findById(Long id);
}
