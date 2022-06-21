package com.jrp.sma.entities;

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

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Student { 

	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "student_seq")
	@SequenceGenerator(name="student_seq", sequenceName="student_seq", allocationSize = 1)

	private long studentId;
	
	private String firstName;
	private String lastName;
	private String email;
	private int age;
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Student(String firstName, String lastName, String email, String photo, List<Activity> activities) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.photo = photo;
		this.activities = activities;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	private String photo;
	

	public String getPhoto() {
		return "/user-photos/" + photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}




	public Student () {
		
	}
	
	
	@SequenceGenerator(name="student_seq", sequenceName="student_seq", allocationSize = 1)
		@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.LAZY) 
		 @JoinTable (name = "activity_student", 
		    joinColumns = @JoinColumn(name = "activity_id"),
		    inverseJoinColumns = @JoinColumn(name = "student_id"))
	@JsonIgnore
	private List <Activity> activities;
	
	

	public List<Activity> getActivities() {
		return activities;
	}

    
	

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}




	public Student(String firstName, String lastName) {
		
		this.firstName = firstName;
		this.lastName = lastName;
	
	}



	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	

	 

	
	
}
