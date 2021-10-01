package com.MoneyTeam.PokemonProject.models;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private List<Party> party;
	@Email
	private String email;
	@Size(min=8, max=200)
	private String password;
	public String getPassConfirm() {
		return passConfirm;
	}


	public void setPassConfirm(String passConfirm) {
		this.passConfirm = passConfirm;
	}


	public List<Party> getParty() {
		return party;
	}


	public void setParty(List<Party> party) {
		this.party = party;
	}


	private Boolean isAdmin = false;
	
	@Column(updatable=false)
	public Date createdAt;
	public Date updatedAt;
	
	
	//getters and setters...
	
	
	@Transient
	private String passConfirm;
	
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }

	
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Boolean getIsAdmin() {
		return isAdmin;
	}


	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
}