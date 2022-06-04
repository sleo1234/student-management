package com.jrp.sma.user;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.jrp.sma.dao.RoleRepository;
import com.jrp.sma.dao.UserRepository;
import com.jrp.sma.entities.Role;
import com.jrp.sma.entities.User;
import com.jrp.sma.services.UserService;

		 @DataJpaTest
		 @AutoConfigureTestDatabase (replace =Replace.NONE)
		@Rollback(false)
		 
		public class UserTests {
		@Autowired RoleRepository roleRepo;
		
		@Autowired UserRepository userRepo;
		
	//	@Autowired BCryptPasswordEncoder bCryptPasswordEncoder;
		
		
		
		
		@Test
		public void assignRolesToUser () {
	    Role roleAdmin = roleRepo.findByName("ADMIN");
	  
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        List<Role> roles = new ArrayList<Role>() ;
		
		roles.add(roleAdmin);

        String rawPassword = "123456";
        
        String endodedPassword = encoder.encode(rawPassword);
        
        
		User user = new User("s123.leo@yahoo.com", rawPassword, "Ione","Ionel", true,roles);
		user.setPassword(endodedPassword);
		 
		userRepo.save(user);
		}
		
		
		
		
		
		
		 }
