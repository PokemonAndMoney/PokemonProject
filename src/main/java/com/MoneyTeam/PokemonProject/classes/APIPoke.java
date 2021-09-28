package com.MoneyTeam.PokemonProject.classes;

import java.util.List;

public class APIPoke {
	
	private List<Ability> abilities;
	private List<Move> moves;
	private List<Stat> stats;
	private Sprite sprites;
	public APIPoke() {}
	
	public List<Ability> getAbilities() {
		return abilities;
	}
	public void setAbilities(List<Ability> abilities) {
		this.abilities = abilities;
	}
	public List<Move> getMoves() {
		return moves;
	}
	public void setMoves(List<Move> moves) {
		this.moves = moves;
	}
	public Sprite getSprites() {
		return sprites;
	}
	public void setSprites(Sprite sprites) {
		this.sprites = sprites;
	}
	public List<Stat> getStats() {
		return stats;
	}
	public void setStats(List<Stat> stats) {
		this.stats = stats;
	}

}
