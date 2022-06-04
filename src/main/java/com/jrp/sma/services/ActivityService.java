package com.jrp.sma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrp.sma.dao.ActivityRepository;
import com.jrp.sma.dto.ChartData;
import com.jrp.sma.dto.TimeChartData;
import com.jrp.sma.entities.Activity;

@Service
public class ActivityService {

@Autowired
  ActivityRepository actRepo;
	public List <Activity> listAll (String keyword){
		
		if (keyword != null) {
			
			return actRepo.search(keyword + "%");
		}
		
		return actRepo.findAll();
	}
	public List<ChartData> getActivityStatus() {
		// TODO Auto-generated method stub
		return actRepo.getActivityStatus();
	}
	public List<TimeChartData> getTimeData() {
		// TODO Auto-generated method stub
		return actRepo.getTimeData();
	}
	
	
	
}
