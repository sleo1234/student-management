package com.jrp.sma.student;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;

import com.jrp.sma.dao.StudentRepository;
import com.jrp.sma.entities.Student;

@DataJpaTest
@AutoConfigureTestDatabase (replace =Replace.NONE)
public class StudentRepositoryTests {

	@Autowired StudentRepository studRepo;
	@Test
	
	public void testFindByEmail() {
		
		Student student = studRepo.findByEmail("safta.leonard@gmail.com");
		
		assertThat(student).isNotNull();
		
	}
	
	
	@Test
	
	public void testFindBeetweenAges() {
		
		Page<Student> students = studRepo.findBetweenAge(2, 30);
		
		assertThat(students).size().isEqualTo(6);
	}
}
