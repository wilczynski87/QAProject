package com.qaproject.demo.controllers;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qaproject.demo.clients.Consumer;
import com.qaproject.demo.repositories.ConsumerRepo;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = {"classpath:test-schema.sql", "classpath:prepopulation.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@AutoConfigureMockMvc
public class ProfConsControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private ConsumerRepo cr;
	
	//need to be refractor because content() do not work?!
	@Test
	public void getClient() throws Exception {
		// Given 
		String email = "First@email.com";
		String password = "password1";
		Consumer foundConsumer = this.cr.findConsumerByEmailAndPassword(email, password);
		
		// When
		RequestBuilder request = get("/getUser/{email}/{password}", email, password);
		
		ResultMatcher status = status().isOk();
//		ResultMatcher resultConsumer = content().json(this.mapper.writeValueAsString(myConsumer));
		
		// Then 
		this.mvc
			.perform(request)
			.andExpect(status)
			;
		
	}

}
