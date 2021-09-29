package com.MoneyTeam.PokemonProject.models;
import java.util.*;

import javax.persistence.*;
@Entity
@Table(name="move")
public class pokeMove {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pokemon_id")
	private Pokemon pokemon;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
