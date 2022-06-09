package com.qaproject.demo.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
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
public class CustomerControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private ConsumerRepo cr;

//	@Test
//	public void registerClient() throws Exception {
//		//Given
//		int consumerID = 7;
//		
//		Consumer consumerToSave = new Consumer();
//		String consumerToSaveJSON = this.mapper.writeValueAsString(consumerToSave);
//		
//		Consumer consumerSaved = new Consumer(consumerID);
//		String consumerSavedJSON = this.mapper.writeValueAsString(consumerSaved);
//		//When
//		RequestBuilder request = post("/consumerSignIn").contentType(MediaType.APPLICATION_JSON).content(consumerToSaveJSON);
//		
//		ResultMatcher responseStatus = status().isCreated();
//		ResultMatcher responseContent = content().json(consumerSavedJSON);
//		//Than
//		this.mvc.perform(request).andExpect(responseStatus).andExpect(responseContent);
//	}
	
	@Test
	public void registerClient() throws Exception {
		//Given
		String consumerID = UUID.randomUUID().toString();
		String email = "my@email.com";
		String password = "1234";
		Consumer consumerToSave = new Consumer(consumerID);
		consumerToSave.setEmail(email);
		consumerToSave.setPassword(password);
//		String givenId = this.cr.findConsumerByEmailAndPassword(email, password).getId();
		Consumer consumerSaved = new Consumer(consumerID);
		consumerSaved.setEmail(email);
		consumerSaved.setPassword(password);
		//When and Then
		this.mvc
            .perform(post("/consumerSignIn")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(consumerToSave)))
            .andExpect(status().isCreated())
//            .andDo(print())
            .andExpect(jsonPath("$.email").value("my@email.com"))
            ;
    }
	
	@Test
	public void loginClient() throws Exception {
		// Given
		String consumerID = "123e4567-e89b-12d3-a456-426614174000";
		Consumer consumer = new Consumer();
		String email = "First@email.com";
		String password = "password1";
		Consumer foundConsumer = this.cr.findConsumerByEmailAndPassword(email, password);
		consumer.setEmail(email);
		consumer.setPassword(password);
		consumer.setFullName("Thirdofer Third");
		consumer.setPhone("3333-333-333");
		String consumerJSON = this.mapper.writeValueAsString(consumer);
		// When
		RequestBuilder request = get("/consumerLogin/{email}/{password}", email, password);
		ResultMatcher responseStatus = status().isOk();
		ResultMatcher responseContent = content().json(this.mapper.writeValueAsString(foundConsumer));
		// Then
		this.mvc
			.perform(request)
			.andExpect(responseStatus)
			.andExpect(responseContent)
			;
	}
	
	//how to check if result is boolian "true"
	@Test
	public void deleteClient() throws Exception {
		// Given
		String email = "First@email.com";
		String password = "password1";
		
		// Then
		this.mvc
			.perform(delete("/consumerDelete/{email}/{password}", email, password))
			.andExpect(status().isGone())
			;
	}
	
	@Test
	public void editClient() throws Exception {
		// Given
		String consumerID = "123e4567-e89b-12d3-a456-426614174000";
		String email = "First@email.com";
		String password = "password1";
		Consumer foundConsumer = this.cr.findConsumerByEmailAndPassword(email, password);
		Consumer consumerChanged = new Consumer();
		consumerChanged.setEmail("SSSS@SSSS.com");
		consumerChanged.setPassword("SSSSSSSS");
		// When and Then
		this.mvc
            .perform(put("/consumerChange/{email}/{password}", email, password)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(foundConsumer)))
            .andExpect(status().isAccepted())
            .andExpect(content().json(this.mapper.writeValueAsString(foundConsumer)));
		
	}
}
