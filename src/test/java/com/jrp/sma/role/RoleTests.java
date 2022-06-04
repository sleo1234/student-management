package com.jrp.sma.role;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.jrp.sma.dao.RoleRepository;
import com.jrp.sma.entities.Role;

@DataJpaTest
@AutoConfigureTestDatabase (replace =Replace.NONE)
@Rollback(false)
public class RoleTests {
	
	@Autowired RoleRepository roleRepo;
	
	@Test
	public void createRoles() {
		
		Role roleAdmin = new Role("ADMIN", "This is admin role");
		Role roleStudent = new Role("STUDENT", "This is student role");
		Role roleTeacher = new Role("TEACHER", "This is teacher role");
		
		List<Role> roles = new ArrayList<Role>() ;
		
		roles.add(roleAdmin);
		roles.add(roleStudent);
		roles.add(roleTeacher);
		
		roleRepo.saveAll(roles);
	}
	
	

}
