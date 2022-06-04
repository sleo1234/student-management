package com.jrp.sma.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jrp.sma.dao.RoleRepository;
import com.jrp.sma.dao.UserRepository;
import com.jrp.sma.entities.Role;
import com.jrp.sma.entities.User;
import com.jrp.sma.services.UserService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)

public class UserServiceTest {
	
	
	@MockBean UserRepository userRepo;
	@MockBean RoleRepository roleRepo;
	//@MockBean BCryptPasswordEncoder bCryptPasswordEncoder;
	@InjectMocks UserService userService;
	
	
	@Test
	
	public void saveUserAndEncryptPassword () {
		
		  Role roleAdmin = roleRepo.findByName("ADMIN");
		  
		    
	        List<Role> roles = new ArrayList<Role>() ;
			
			roles.add(roleAdmin);

	        
			User user = new User("saaa.leo@yahoo.com", "123457", "Iona","Ion", true,roles);
		
			User savedUser = userService.save(user);
			System.out.println("-------------------------: " + savedUser);
			//assertThat(savedUser).isNotNull();
		  
		//assertThat(savedUser.getPassword()).isG
		
	}
	

}
