package com.jrp.sma.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jrp.sma.dao.UserRepository;
import com.jrp.sma.entities.User;

@Service
public class ManageUserDetailService implements UserDetailsService{

	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		 User user	= userRepo.findByEmail(email);
         if (user != null) {
       	  System.out.println("User with the email: " + email);
	            return new ManageUserDetails(user);
              }
         throw new UsernameNotFoundException ("Could not find user with email: " + email);
	}

}
