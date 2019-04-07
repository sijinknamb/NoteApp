package com.note.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.note.model.User;
import com.note.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	public void saveUser(User user) {
       
		userRepo.save(user);
    }
	
	public User findUser(String name) {
 
		return userRepo.findByName(name);
		
	}
}
