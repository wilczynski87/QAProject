package com.qaproject.demo.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qaproject.demo.clients.Consumer;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = {"classpath:test-schema.sql", "classpath:prepopulation.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@AutoConfigureMockMvc
public class CustomerControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;

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
//	
//	@Test
//	public void registerClient() throws Exception {
//		//Given
////		int consumerID = 5;
//		String consumerID = UUID.randomUUID().toString();
//		String email = "my@email.com";
//		String password = "1234";
//		Consumer consumerToSave = new Consumer();
//		consumerToSave.setEmail(email);
//		consumerToSave.setPassword(password);
//		Consumer consumerSaved = new Consumer(consumerID);
//		consumerSaved.setEmail(email);
//		consumerSaved.setPassword(password);
//		//When and Then
//		this.mvc
//            .perform(post("/consumerSignIn")
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(this.mapper.writeValueAsString(consumerToSave)))
//            .andExpect(status().isCreated())
//            .andExpect(content().json(this.mapper.writeValueAsString(consumerSaved)));
//    }
	
//	@Test
//	public void loginClient() throws Exception {
//		//Given
//		int consumerID = 3;
//		Consumer consumer = new Consumer(consumerID);
//		consumer.setEmail("Third@email.com");
//		consumer.setPassword("password3");
//		consumer.setAddress("30 Third street S33 3SS London");
//		consumer.setFullName("Thirdofer Third");
//		consumer.setPhone("3333-333-333");
//		String consumerJSON = this.mapper.writeValueAsString(consumer);
//		//When
//		RequestBuilder request = get("/consumerLogin/Third@email.com/password3");
//		
//		ResultMatcher responseStatus = status().isOk();
//		ResultMatcher responseContent = content().json(consumerJSON);
//		//Then
//		this.mvc.perform(request).andExpect(responseStatus).andExpect(responseContent);
//	}
	
//	@Test
//	public void loginClient2() throws Exception {
//		//Given
////		int consumerID = 4;
//		String consumerID = UUID.randomUUID().toString();
//		Consumer consumer = new Consumer(consumerID);
//		consumer.setEmail("email@email.com");
//		consumer.setPassword("password");
//		System.out.println(consumer.toString());
//		//When and Than
//		this.mvc
//			.perform(get("/consumerLogin/email@email.com/password"))
//			.andExpect(status().isOk())
//			.andExpect(content().json(this.mapper.writeValueAsString(consumer)));
//	}
//	
//	@Test
//	public void deleteClient() throws Exception {
//		this.mvc
//			.perform(delete("/consumerDelete/email@email.com/password"))
//			.andExpect(status().isGone());
//	}
//	
//	@Test
//	public void editClient() throws Exception {
//		//Given
////		int consumerID = 4;
//		String consumerID = UUID.randomUUID().toString();
//		Consumer consumerToChange = new Consumer(consumerID);
//		consumerToChange.setEmail("email@email.com");
//		consumerToChange.setPassword("password");
//		Consumer consumerChanged = new Consumer(consumerID);
//		consumerChanged.setEmail("SSSS@SSSS.com");
//		consumerChanged.setPassword("SSSSSSSS");
//		//When and Then
//		this.mvc
//            .perform(put("/consumerChange/email@email.com/password")
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(this.mapper.writeValueAsString(consumerChanged)))
//            .andExpect(status().isAccepted())
//            .andExpect(content().json(this.mapper.writeValueAsString(consumerChanged)));
//		
//	}
}
