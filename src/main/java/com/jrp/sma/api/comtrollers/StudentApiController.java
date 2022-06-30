package com.jrp.sma.api.comtrollers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.jrp.sma.controllers.StudentController;
import com.jrp.sma.dao.StudentRepository;
import com.jrp.sma.entities.Student;
import com.jrp.sma.services.StudentService;

@RestController
@RequestMapping("/api/students")

public class StudentApiController {

	 @Autowired
	 StudentRepository studRepo;
	 
	 @Autowired StudentService studService;
	 
	 @Autowired StudentController studController;
	
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
	
	@GetMapping("/filter_by_age/page/{pageNum}")
	
	public List<Student> filterByAge (@PathVariable("pageNum") int pageNum, @Param("keyword") String keyword,@Param("sortField") String sortField,
			@Param("sortDir") String sortDir, @Param("minAge") Integer minAge,
			@Param("maxAge") Integer maxAge) {
		
		  System.out.println("++++++++++" + minAge);
          System.out.println("++++++++++" + maxAge);
		 if (minAge == null || maxAge == null) {
           List<Integer> ages = studController.checkNullAgeValues(minAge, maxAge);
            
          minAge = ages.get(0);
           maxAge = ages.get(1);
		      }
		 
         List<Student> listOfStudents = new ArrayList<Student>();
         
 		if (minAge != null && maxAge != null) {
 			Page<Student> page = studService.listByPage(pageNum, sortField, sortDir, null, minAge, maxAge);
            System.out.println("++++++++++" + minAge);
            System.out.println("++++++++++" + maxAge);
 			List<Student> listStudents = page.getContent();
 			
 			for (Student stud : listStudents) {
 				listOfStudents.add(stud);
 			}
 			

 			long startCount = (pageNum - 1) * StudentService.STUDENTS_PER_PAGE + 1;
 			long endCount = startCount + StudentService.STUDENTS_PER_PAGE + 1;
 			
 			if (endCount > page.getTotalElements()) {
 				endCount = page.getTotalElements();
 			}
 			
 		
         System.out.println("----------------------------------------" + listOfStudents.size());
 			
	}
 		return listOfStudents;
	
	}
	
	
}
