package com.jrp.sma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.sma.dao.StudentRepository;
import com.jrp.sma.dto.ChartData;
import com.jrp.sma.dto.ActivityStudent;
import com.jrp.sma.entities.Student;
import com.jrp.sma.services.ActivityService;
import com.jrp.sma.services.StudentService;

import netscape.javascript.JSException;

@Controller

public class HomeController {
	
	@Autowired
	StudentRepository studRepo;
	
	 @Autowired StudentService studService;
	 
	@Autowired
	ActivityService actService;
	
	@GetMapping("/")
	
	public String displayHomePage(Model model) throws JsonProcessingException{
		
		List <Student> students = studService.listAll();
		model.addAttribute("students",students);
		List <ActivityStudent> studentActivities = studRepo.activityStudents();
		model.addAttribute("StudentActivityCount", studentActivities);
		List <ChartData> activityData = actService.getActivityStatus();
		ObjectMapper objectMapper = new ObjectMapper ();
		
		String jsonString = objectMapper.writeValueAsString(activityData);
		
		System.out.println(jsonString);
		return "main/home";
	}

}
