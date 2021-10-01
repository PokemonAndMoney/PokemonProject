package com.MoneyTeam.PokemonProject.services;

import org.springframework.stereotype.Service;

import com.MoneyTeam.PokemonProject.models.User;
import com.MoneyTeam.PokemonProject.repositories.*;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;//for hashing password

@Service
public class UserService{
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
    public User findUserByEmail(String email) {
    	return this.userRepository.findByEmail(email);
    }
    public User findUserById(Long id) {
    	Optional<User> u = userRepository.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }

    
    
	//registration
	public User register(User user) {
		if(this.findUserByEmail(user.getEmail()) == null) {
			String hash_pw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
			user.setPassword(hash_pw);
			user = this.userRepository.save(user);
			//setting admin
			if(user.getId() == 1) {
				user.setIsAdmin(true);
				this.userRepository.save(user);
			}
			user.setIsAdmin(false);
			return user;
		}
		//returns null if registered email is already found in database
		return null;
	}
	public User login (User user) {
		User found = this.findUserByEmail(user.getEmail());
		//check if email is present, then check matching password
		if (found == null || !BCrypt.checkpw(user.getPassword(), found.getPassword())) return null;
		return found;
	}
	//does email exist?
	//public User register(org.springframework.boot.autoconfigure.security.SecurityProperties.User user) {
	//	if(this.findUserByEmail(user.getEmail()) == null) {
	//		String hashed = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt());
	//		user.setPassword(hashed);
	//		return this.userRepository.save(user);
	//	}
	//}
	//hash password
	//save new user
	//check if first user, if so, make them admin
	//create session
}