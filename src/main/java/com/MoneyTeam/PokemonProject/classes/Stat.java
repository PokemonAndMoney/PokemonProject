package com.MoneyTeam.PokemonProject.classes;

public class Stat {
	private int base_stat;
	private StatStat stat;
	
	public Stat() {}

	public int getBase_stat() {
		return base_stat;
	}
	public void setBase_stat(int base_stat) {
		this.base_stat = base_stat;
	}
	public StatStat getStat() {
		return stat;
	}
	public void setStat(StatStat stat) {
		this.stat = stat;
	}
	
}
