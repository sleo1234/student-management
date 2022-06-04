package com.jrp.sma.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jrp.sma.dto.ActivityStudent;
import com.jrp.sma.entities.Student;

@RepositoryRestResource
(collectionResourceRel = "apistudents", path = "apistudents")


public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM student WHERE first_name = ?1 OR last_name = ?1")
	public Page <Student> findAll(@Param ("keyword") String keyword, Pageable pageable);
	
	@Query (nativeQuery = true, value = "SELECT s.FIRST_NAME as firstName , s.LAST_NAME as lastName , COUNT(sa.STUDENT_ID) as activityCount "
			+" FROM STUDENT s left join ACTIVITY_STUDENT sa ON sa.STUDENT_ID = s.STUDENT_ID  "
		   + " GROUP BY s.FIRST_NAME ,s.LAST_NAME  ORDER BY 3 ")
	
	public List <ActivityStudent> activityStudents ();

	public Student findByStudentId(long studentId); 

	@Query (nativeQuery = true, value = "SELECT * FROM STUDENT WHERE first_name "
			+ "LIKE UPPER (?1) OR first_name LIKE LOWER (?1) "
			+ "OR last_name LIKE LOWER (?1) OR last_name LIKE UPPER(?1)")
	public List <Student> search (@Param("keyword") String keyword);
	
	
	public Student findByEmail(String email);

	
	
}
