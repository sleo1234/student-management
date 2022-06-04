package com.jrp.sma.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


	@Entity
	@Table (name = "users")
	public class User {

		@Id 
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
		@SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
		private long id;
		
		@Column (length = 128, nullable = false, unique = true)
		private String email;
		
		@Column (length = 64, nullable = false)
		private String password;
		
		@Column (name = "first_name",length = 45, nullable = false)
		private String firstName;
		
		@Column (name = "last_name",length = 45, nullable = false)
		private String lastName;
		
		private boolean enabled;
	
		

		@ManyToMany(fetch = FetchType.EAGER)
		//targetEntity = com.shopme.common.entity.Role.class
				
		@JoinTable(name = "users_roles",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id"))
		private List<Role> roles = new ArrayList<Role>();

        

		public User() {
			
		}



		public User(String email, String password, String firstName, String lastName, boolean enabled,
				List<Role> roles) {
		
			this.email = email;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
			this.enabled = enabled;
			this.roles = roles;
		}



		public long getId() {
			return id;
		}



		public void setId(long id) {
			this.id = id;
		}



		public String getEmail() {
			return email;
		}



		public void setEmail(String email) {
			this.email = email;
		}



		public String getPassword() {
			return password;
		}



		public void setPassword(String password) {
			this.password = password;
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



		public boolean isEnabled() {
			return enabled;
		}



		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}



		public List<Role> getRoles() {
			return roles;
		}



		public void setRoles(List<Role> roles) {
			this.roles = roles;
		}
		
		
		


}
