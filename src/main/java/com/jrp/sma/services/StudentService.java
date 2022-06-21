package com.jrp.sma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jrp.sma.dao.StudentRepository;
import com.jrp.sma.entities.Student;

@Service
public class StudentService {

	@Autowired
	StudentRepository studRepo;
	
	public static final int STUDENTS_PER_PAGE = 4;
	
	public List <Student> listAll (){
		
		return (List<Student>) studRepo.findAll();
	
		
	}
     
	public Page <Student> listBetweenAge(int pageNum, String sortField, String sortDir,int minAge, int maxAge){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNum - 1, STUDENTS_PER_PAGE, sort);
		if (minAge==0 && maxAge==0) {
			return studRepo.findAll(pageable);
		}
		return studRepo.findBetweenAge(minAge, maxAge, pageable);
     }

	

	
	public Page<Student> listByPage(int pageNum, String sortField, String sortDir, String keyword){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNum - 1, STUDENTS_PER_PAGE, sort);
		
	    if (keyword != null) {
				
			return  studRepo.findAll(keyword, pageable);
		}
		return  studRepo.findAll(pageable);
	}
	
	public boolean isEmailUnique (String email) {
		
		Student student = studRepo.findByEmail(email);
		
		
		return student == null ;
	}
	

	/*
	 * public List <Student> listByName (String name){
	 * 
	 * if (name != null) {
	 * //System.out.println(studRepo.findByName(name).toString()); return
	 * studRepo.findByName(name); } return studRepo.findAll(); }
	 */
	
	
}
