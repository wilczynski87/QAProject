package com.qaproject.demo.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qaproject.demo.auctions.Auction;
import com.qaproject.demo.auctions.Bid;
import com.qaproject.demo.clients.Consumer;
import com.qaproject.demo.clients.Professional;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = {"classpath:test-schema.sql", "classpath:prepopulation.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@AutoConfigureMockMvc
public class AuctionControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapped;
	
	@Test
	public void createAuction() throws JsonProcessingException, Exception {
		//Given
		Consumer consumer = new Consumer(1);
		Auction auction = new Auction(consumer);
		auction.setId(4);
		//When and Than
		this.mvc
			.perform(post("/createAuction/1")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.mapped.writeValueAsString(auction)))
			.andExpect(status().isCreated())
			.andExpect(content().json(mapped.writeValueAsString(auction)));
	}
	
	@Disabled
	@Test
	public void getAuctions() throws Exception {
		//Given
		Consumer consumer = new Consumer(1);
		Auction auction1 = new Auction(consumer);
		auction1.setId(1);
		Auction auction2 = new Auction(consumer);
		auction2.setId(2);
		List<Auction> list = new ArrayList<>(); 
		list.add(auction1);
		list.add(auction2);
		Professional prof = new Professional(1);
		Bid bid1 = new Bid(999, prof, auction1);
		bid1.setId(1);
		Bid bid2 = new Bid(100, prof, auction1);
		bid2.setId(2);
		
		
		//When and Then
		this.mvc
			.perform(get("/getAuctionsByConsumerId/1"))
			.andExpect(status().isAccepted())
			.andExpect(content().json(mapped.writeValueAsString(list)));
	}
}
