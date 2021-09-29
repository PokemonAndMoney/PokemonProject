package com.MoneyTeam.PokemonProject.models;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="parties")
public class Party {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@OneToMany(mappedBy="party", fetch = FetchType.LAZY)
	private List<Pokemon> pokemon;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
	private User user;

	@Column(updatable=false)
	public Date createdAt;
	public Date updatedAt;
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
	public List<Pokemon> getPokemon() {
		return pokemon;
	}
	public void setPokemon(List<Pokemon> pokemon) {
		this.pokemon = pokemon;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }

	
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}