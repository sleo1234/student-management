package com.jrp.sma.student;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
		
		Sort sort = Sort.by("first_name");
		
		List<Student> allStudents = (List<Student>) studRepo.findAll();
		Pageable pageable = PageRequest.of(1,allStudents.size(), sort.ascending());
		
		Page<Student> students = studRepo.findBetweenAge(2, 30,pageable);
		
		students.forEach(System.out :: println);
		
		assertThat(students).size().isNotNull();
	}
	
	
}
