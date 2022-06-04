package com.jrp.sma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.sma.dao.ActivityRepository;
import com.jrp.sma.dao.StudentRepository;
import com.jrp.sma.dto.TimeChartData;
import com.jrp.sma.entities.Activity;
import com.jrp.sma.entities.Student;
import com.jrp.sma.services.ActivityService;
import com.jrp.sma.services.StudentService;

@Controller
@RequestMapping("/activities")
public class ActivityController {

	@Autowired
	ActivityRepository actRepo;
	
	@Autowired
	private StudentRepository studRepo;
	
	@Autowired private StudentService studService;
	
	@Autowired
	ActivityService actService;
	
	@RequestMapping
	public String displayActivity (Model model, @Param("keyword") String keyword) {
		List <Activity> activities = actService.listAll(keyword);
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("activities", activities);
		return "activities/activitiesList";
	}
	
	@GetMapping("/new")
	
	public String addActivity (Model model) {
		
		Activity activity = new Activity();
		model.addAttribute("activity",activity);
		
		List <Student> students = studService.listAll();
		model.addAttribute("students", students);		
	return "activities/new-Activity";
	}
	
	@PostMapping("/save")
	
	public String saveActivity (Model model, Activity activity) {
		actRepo.save(activity);
		
		return "redirect:/activities/new";
	}
	
	@GetMapping("/update")
	public String updateActivity (@RequestParam("id") long id, Model model) {
		Activity anActivity = actRepo.findByActivityId(id);
		model.addAttribute("activity", anActivity);
	
		return "activities/new-Activity";
	}
	
	@GetMapping("/delete")
	public String deleteActivity (@RequestParam("id") long id, Model model) {
    Activity theActivity = actRepo.findByActivityId(id);
    actRepo.delete(theActivity);
    model.addAttribute("theActivity", theActivity);
		return "redirect:/activities";
	} 
	
	 @GetMapping ("/activity-timelines")
	    public String getProjectTimelines(Model model) throws JsonProcessingException{
	    	List <TimeChartData> timelineData = actService.getTimeData();
	        ObjectMapper objectMapper = new ObjectMapper ();
	       String jsonTimeLineString = objectMapper.writeValueAsString(timelineData);
	        System.out.println("---------------activity timelines---------");
	        System.out.println(jsonTimeLineString);
	       model.addAttribute("activityTimeList",jsonTimeLineString);
	    	
	    return "activities/activity-timelines";
	    }
	

}
