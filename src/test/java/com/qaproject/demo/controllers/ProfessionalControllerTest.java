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
	
	//need to do different test! this do not have any sense!
	@Test
	public void registerClient() throws Exception {
		//Given
		String profId = UUID.randomUUID().toString();
		Professional profToSave = new Professional(profId);
		Professional profSaved = new Professional(profId);
		//When and Then
		this.mvc
            .perform(post("/professionalSignIn")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(profToSave)))
            .andExpect(status().isCreated())
            .andExpect(content().json(this.mapper.writeValueAsString(profSaved)));
    }
	
	@Test
	public void loginClient2() throws Exception {
		//Given
		Professional consumer = new Professional();
		consumer.setEmail("email@email.com");
		consumer.setPassword("password");
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
		String profId = UUID.randomUUID().toString();
		Professional consumerToChange = new Professional(profId);
		consumerToChange.setEmail("email@email.com");
		consumerToChange.setPassword("password");
		Professional consumerChanged = new Professional(profId);
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
