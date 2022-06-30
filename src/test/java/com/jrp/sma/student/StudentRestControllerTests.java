package com.jrp.sma.student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.sma.dao.StudentRepository;
import com.jrp.sma.entities.Student;

@SpringBootTest
@AutoConfigureMockMvc
@Rollback(false)
public class StudentRestControllerTests {
	
    @Autowired MockMvc mockMvc;
	@Autowired ObjectMapper objectMapper;
	@Autowired StudentRepository studRepo;
	
	       
	@Test
	@WithMockUser(username = "leo@yahoo.com", password = "leo2020", roles = "ADMIN")
	public void testCheckUniqueEmailServiceAndExpectOKOrDuplicated () throws JsonProcessingException, Exception {
		String url = "/api/students/check_email_unique";
		
		
		String email ="safta.leonard@gmail.com";
		 
		 MvcResult result = mockMvc.perform(post(url).contentType("application/json")
				 .content(objectMapper.writeValueAsString(email)).with(csrf()))
		 .andDo(print())
		 .andExpect(status().isOk()).andDo(print())
		 .andReturn();
		
		 String response = result.getResponse().getContentAsString();
		 assertThat(response.contains("OK"));
	
	}
	
	@Test
	@WithMockUser(username = "leo@yahoo.com", password = "leo2020", roles = "ADMIN")
	
	public void testFilterByAge() throws JsonProcessingException, Exception{
		
		Integer minAge=20;
		Integer maxAge=23;
		int pageNum=1;
		String url = "/api/students/filter_by_age/page/"+pageNum+
				"?keyword=&sortField=first_name&sortDir=asc&minAge="+ minAge+
	            "&maxAge=" + maxAge;
		
		MvcResult result = mockMvc.perform(get(url).with(csrf())).andDo(print()) 
				.andExpect(status().isOk()).andDo(print())
				 .andReturn();
		
		String response = result.getResponse().getContentAsString();
		
		List<Student> students =objectMapper.readValue(response,new TypeReference<List<Student>>() {
		});
		
		
		assertThat(students).size().isEqualTo(3);
	}
	
	
	
	
}
