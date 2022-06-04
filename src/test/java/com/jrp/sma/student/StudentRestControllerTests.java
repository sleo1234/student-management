package com.jrp.sma.student;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.sma.dao.StudentRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Rollback(false)
public class StudentRestControllerTests {
	
    @Autowired MockMvc mockMvc;
	@Autowired ObjectMapper objectMapper;
	@Autowired StudentRepository studRepo;
	
	       
	@Test
	@WithMockUser(username = "nam@codejava.net", password = "nam2020", roles = "ADMIN")
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

	
	
	
	
}
