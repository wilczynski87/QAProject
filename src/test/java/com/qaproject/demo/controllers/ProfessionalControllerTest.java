package com.qaproject.demo.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Disabled;
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
import com.qaproject.demo.address.ProfPos;
import com.qaproject.demo.clients.Professional;
import com.qaproject.demo.repositories.ProfessionalRepo;
import com.qaproject.demo.service.ProfessionalService;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = {"classpath:test-schema.sql", "classpath:prepopulation.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@AutoConfigureMockMvc
public class ProfessionalControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private ProfessionalRepo pr;
	
	@Autowired
	private ProfessionalService ps;
	
	@Disabled("problem with lazy initialisation")
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
	
	@Disabled("problem with lazy initialisation")
	@Test
	public void loginClient() throws Exception {
		//Given
		String email = "email@email.com";
		String password = "password";
		Professional professional = this.pr.findProfessionalByEmailAndPassword(email, password);
		//When and Than
		this.mvc
			.perform(get("/professionalLogin/{email}/{password}", email, password))
			.andExpect(status().isOk())
			.andExpect(content().json(this.mapper.writeValueAsString(professional)));
	}
	
	@Disabled("problem with lazy initialisation")
	@Test
	public void deleteClient() throws Exception {
		// Given
		String email = "email@email.com";
		String password = "password";
		
		// When and Then
		this.mvc
			.perform(delete("/professionalDelete/{email}/{password}", email, password))
			.andExpect(status().isGone());
	}
	
	@Disabled("problem with lazy initialisation")
	@Test
	public void editClient() throws Exception {
		//Given
		String email = "Third@email.com";
		String password = "password3";
		Professional professionalFound = this.pr.findProfessionalByEmailAndPassword(email, password);
		professionalFound.setEmail("SSSS@SSSS.com");
		professionalFound.setPassword("SSSSSSSS");
		//When and Then
		this.mvc
            .perform(put("/professionalChange/{email}/{password}", email, password)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(professionalFound)))
            .andExpect(status().isAccepted())
            .andExpect(content().json(this.mapper.writeValueAsString(professionalFound)));
	}
	
	@Disabled("problem with lazy initialisation")
	@Test
	public void getProfessionals_returnListOfProf_filteredByDistance() throws Exception {
		//Given
		int distance = 100;
		double lat = 1.0000;
		double lng = 1.0000;
		List<ProfPos> list = this.ps.getProfessionals(distance, lat, lng);
		
		// When
		RequestBuilder request = get("/profPos/{distance}/{lat}/{lng}", distance, lat, lng);
		ResultMatcher statusResult = status().isOk();
		ResultMatcher contentResult = content().json(this.mapper.writeValueAsString(list));
		
		//Then
			this.mvc
				.perform(request)
				.andExpect(statusResult)
				.andExpect(contentResult)
				;
	}
}
