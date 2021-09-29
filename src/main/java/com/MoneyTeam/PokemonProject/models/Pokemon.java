package com.MoneyTeam.PokemonProject.models;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="pokemon")
public class Pokemon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String ability;
	@OneToMany(mappedBy="pokemon", fetch = FetchType.LAZY)
	private List<pokeType> types;
	@OneToMany(mappedBy="pokemon", fetch = FetchType.LAZY)
	private List<pokeMove> moves;
	@OneToMany(mappedBy="pokemon", fetch = FetchType.LAZY)
	private List<pokeStat> stats;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="party_id")
    private Party party;

	public Pokemon() {}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbility() {
		return ability;
	}
	public void setAbility(String ability) {
		this.ability = ability;
	}
	public List<pokeType> getTypes() {
		return types;
	}
	public void setTypes(List<pokeType> types) {
		this.types = types;
	}
	public List<pokeMove> getMoves() {
		return moves;
	}
	public void setMoves(List<pokeMove> moves) {
		this.moves = moves;
	}
	public List<pokeStat> getStats() {
		return stats;
	}
	public void setStats(List<pokeStat> stats) {
		this.stats = stats;
	}
}