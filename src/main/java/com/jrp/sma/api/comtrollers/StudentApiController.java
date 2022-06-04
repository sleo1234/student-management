package com.jrp.sma.api.comtrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jrp.sma.dao.StudentRepository;
import com.jrp.sma.entities.Student;
import com.jrp.sma.services.StudentService;

@RestController
@RequestMapping("/api/students")

public class StudentApiController {

	 @Autowired
	 StudentRepository studRepo;
	 
	 @Autowired StudentService studService;
	
	@GetMapping
	
	public List <Student> displayStudents () {
		
		return (List<Student>)studService.listAll();
	}
	
	@GetMapping("/{id}")
	
	public Student getStudentById (@PathVariable ("id") Long id) {
		
		return studRepo.findByStudentId(id);
	}
	
	
	@PostMapping (consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Student createStudent (@RequestBody Student student) {
		
		return studRepo.save(student);
	}
	

	@PutMapping (consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Student updateStudent (@RequestBody Student student) {
		
		return studRepo.save(student);
	}
	
	@PatchMapping(consumes = "application/json")
	@ResponseStatus (HttpStatus.OK)
	public Student partialUpdateStudent (@PathVariable ("id") Long id, @RequestBody Student patchStudent) {
		Student student = studRepo.findByStudentId(id); //find student that you want to update
		
		if (patchStudent.getFirstName()!= null) {
			
			student.setFirstName(patchStudent.getFirstName());
		}
		
       if (patchStudent.getLastName()!= null) {
			
			student.setLastName(patchStudent.getLastName());
		}
    
       
       
		return studRepo.save(student);
	}
	
	@DeleteMapping(path = "/{id}", consumes = "application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	
	public void deleteStudent (@PathVariable("id") Long id) {
		try {
			studRepo.deleteById(id);
			
		}
		
		catch (org.springframework.dao.EmptyResultDataAccessException e) {
			
		}
	}
	
	@PostMapping("/check_email_unique")
	
	public String checkEmailUnique(@Param("email") String email) {
		
		
		return studService.isEmailUnique(email) ? "OK" : "Duplicated";
	}
	
	
	
}
