package com.qaproject.demo.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qaproject.demo.auctions.Auction;
import com.qaproject.demo.auctions.Bid;
import com.qaproject.demo.clients.Consumer;
import com.qaproject.demo.clients.Professional;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:test-schema.sql", "classpath:prepopulation.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class BidControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Disabled("Early Deployment")
	@Test 
	public void makeBid() throws JsonProcessingException, Exception {
		//Given
		String profId = UUID.randomUUID().toString();
		String consId = UUID.randomUUID().toString();
		Professional prof = new Professional(profId);
		Consumer consumer = new Consumer(consId);
		Auction auction = new Auction(consumer);
		Bid bid = new Bid(100, prof, auction);
		bid.setId(5);
		//When and Then
		this.mvc
			.perform(post("/makeBid/1/1")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_PROBLEM_JSON)
				.content(mapper.writeValueAsString(bid)))
			.andExpect(status().isCreated())
			.andExpect(content().json(mapper.writeValueAsString(bid)));
	}
	
	@Disabled("Too nested")
	@Test
	public void getBidsByAuctionId() throws JsonProcessingException, Exception {
		//Given
		Bid bid = new Bid();
		String consId = UUID.randomUUID().toString();
		Consumer consumer = new Consumer(consId);
		Auction auction1 = new Auction(consumer);
		auction1.setId(1);
		bid.setAuction(auction1);
		List<Bid> list = new ArrayList<>();
		list.add(bid);
		//When and Then
		this.mvc
			.perform(get("/getBids/2"))
			.andExpect(status().isAccepted())
			.andExpect(content().json(mapper.writeValueAsString(list)));
	}
}
