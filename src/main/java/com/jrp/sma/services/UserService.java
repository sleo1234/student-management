package com.jrp.sma.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jrp.sma.dao.UserRepository;
import com.jrp.sma.entities.User;

@Service
@Transactional
public class UserService {

	
	@Autowired private UserRepository userRepo;

	
	
	//@Autowired BCryptPasswordEncoder passwordEncoder;

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	private void encodePassword(User user) {
		
		System.out.println("===================: " + user.getPassword());
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		System.out.println("Encoded password is: " + encodedPassword);
	}
	
	public User save(User user) {
		
		encodePassword(user);
		User savedUser = userRepo.save(user);
		return savedUser;
		
	}
	
	
	
	
}
