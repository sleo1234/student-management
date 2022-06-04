package com.jrp.sma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jrp.sma.dto.ChartData;
import com.jrp.sma.dto.TimeChartData;
import com.jrp.sma.entities.Activity;


public interface ActivityRepository extends CrudRepository<Activity, Long> {

	@Override
	public List <Activity> findAll(); 
	
	@Query (nativeQuery = true, value = "SELECT * FROM ACTIVITY WHERE name LIKE UPPER (?1) or name LIKE LOWER (?1)")
	public List <Activity> search(@Param ("keyword") String keyword);

	public Activity findByActivityId(long id);
	
	@Query (nativeQuery = true, value = "SELECT name as activityName, start_date as startDate, end_date as endDate FROM activity")
	public List <TimeChartData> getTimeData();
	
	@Query (nativeQuery = true, value = "select name as label, count (*) as value from activity group by name")
	public List <ChartData> getActivityStatus ();
	
	
}
