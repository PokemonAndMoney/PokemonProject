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
	
//	@OneToMany(mappedBy="pokemon", fetch = FetchType.LAZY)
//	private List<pokeType> types;
	private String type1;
	private String type2;
//	@OneToMany(mappedBy="pokemon", fetch = FetchType.LAZY)
//	private List<pokeMove> moves;
	private String move1;
	private String move2;
	private String move3;
	private String move4;
	
//	@OneToMany(mappedBy="pokemon", fetch = FetchType.LAZY)
//	private List<pokeStat> stats;
	private int hp;
	private int atk;
	private int def;
	private int spAtk;
	private int spDef;
	private int spd;
	
	private String img;
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

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public String getMove1() {
		return move1;
	}

	public void setMove1(String move1) {
		this.move1 = move1;
	}

	public String getMove2() {
		return move2;
	}

	public void setMove2(String move2) {
		this.move2 = move2;
	}

	public String getMove3() {
		return move3;
	}

	public void setMove3(String move3) {
		this.move3 = move3;
	}

	public String getMove4() {
		return move4;
	}

	public void setMove4(String move4) {
		this.move4 = move4;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getSpAtk() {
		return spAtk;
	}

	public void setSpAtk(int spAtk) {
		this.spAtk = spAtk;
	}

	public int getSpDef() {
		return spDef;
	}

	public void setSpDef(int spDef) {
		this.spDef = spDef;
	}

	public int getSpd() {
		return spd;
	}

	public void setSpd(int spd) {
		this.spd = spd;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	
}