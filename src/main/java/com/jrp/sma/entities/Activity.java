package com.jrp.sma.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Activity {

	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "activity_seq")
	@SequenceGenerator(name="activity_seq", sequenceName="activity_seq", allocationSize = 1)
	
   
	private long activityId;
	
	private String name;
	private String description;
	private Date endDate;
	private Date startDate;
	

	 @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.LAZY) 
		
	    @JoinTable (name = "activity_student",
	    joinColumns = @JoinColumn(name = "activity_id"),
	    inverseJoinColumns = @JoinColumn(name = "student_id"))

	 private List <Student> students;
	
	
	


	public List<Student> getStudents() {
		return students;
		
	}



	public void setStudents(List<Student> students) {
		this.students = students;
	}



	public Activity() {
		
	}
	
	

	public Activity(String name, String description, Date endDate, Date startDate) {
	
		this.name = name;
		this.description = description;
		this.endDate = endDate;
		this.startDate = startDate;
	}



	public long getActivityId() {
		return activityId;
	}

	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



	@Override
	public String toString() {
		return "Activity [activityId=" + activityId + ", name=" + name + ", description=" + description + ", endDate="
				+ endDate + ", startDate=" + startDate + ", students=" + students + "]";
	}


	
	
}
