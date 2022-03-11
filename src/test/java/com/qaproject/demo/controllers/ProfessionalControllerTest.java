package com.qaproject.demo.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.qaproject.demo.clients.Professional;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = {"classpath:test-schema.sql", "classpath:prepopulation.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@AutoConfigureMockMvc
public class ProfessionalControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	public void registerClient() throws Exception {
		//Given
		int consumerID = 5;
		Professional consumerToSave = new Professional();
		Professional consumerSaved = new Professional(consumerID);
		//When and Then
		this.mvc
            .perform(post("/professionalSignIn")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(consumerToSave)))
            .andExpect(status().isCreated())
            .andExpect(content().json(this.mapper.writeValueAsString(consumerSaved)));
    }
	
	@Test
	public void loginClient2() throws Exception {
		//Given
		int consumerID = 4;
		Professional consumer = new Professional(consumerID);
		consumer.setEmail("email@email.com");
		consumer.setPassword("password");
		System.out.println(consumer.toString());
		//When and Than
		this.mvc
			.perform(get("/professionalLogin/email@email.com/password"))
			.andExpect(status().isOk())
			.andExpect(content().json(this.mapper.writeValueAsString(consumer)));
	}
	
	@Test
	public void deleteClient() throws Exception {
		this.mvc
			.perform(delete("/professionalDelete/4"))
			.andExpect(status().isGone());
	}
	
	@Test
	public void editClient() throws Exception {
		//Given
		int consumerID = 5;
		Professional consumerToChange = new Professional(consumerID);
		consumerToChange.setEmail("email@email.com");
		consumerToChange.setPassword("password");
		Professional consumerChanged = new Professional(consumerID);
		consumerChanged.setEmail("SSSS@SSSS.com");
		consumerChanged.setPassword("SSSSSSSS");
		//When and Then
		this.mvc
            .perform(put("/professionalChange/email@email.com/password")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(consumerChanged)))
            .andExpect(status().isAccepted())
            .andExpect(content().json(this.mapper.writeValueAsString(consumerChanged)));
		
	}
}
