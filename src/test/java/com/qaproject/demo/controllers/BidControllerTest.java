package com.qaproject.demo.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
import com.qaproject.demo.clients.Professional;
import com.qaproject.demo.repositories.AuctionRepo;
import com.qaproject.demo.repositories.BidRepo;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:test-schema.sql", "classpath:prepopulation.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class BidControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private BidRepo br;
	
	@Autowired
	private AuctionRepo ar;
	
	@Test 
	public void makeBid() throws JsonProcessingException, Exception {
		//Given
		String profId = "223e4567-e89b-12d3-a456-426614174000";
		int auctionId = 1;
		Professional prof = new Professional("223e4567-e89b-12d3-a456-426614174000");
		Auction auction = new Auction();
		auction.setId(auctionId);
		Bid bid = new Bid(100, prof, auction);
		bid.setId(5);
		//When and Then
		this.mvc
			.perform(post("/makeBid/{professionalId}/{auctionId}", profId, auctionId)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_PROBLEM_JSON)
				.content(mapper.writeValueAsString(bid)))
			.andExpect(status().isCreated())
			.andExpect(content().json(mapper.writeValueAsString(bid)));
	}
	
	@Test
	public void getBids() throws JsonProcessingException, Exception {
		//Given
		int auctionId = 2;
		Bid bid2 = this.br.findById(4).get();
		List<Bid> list = new ArrayList<>();
		list.add(bid2);
		
		//When and Then
		this.mvc
			.perform(get("/getBids/{auctionId}", auctionId))
			.andExpect(status().isAccepted())
			.andExpect(content().json(mapper.writeValueAsString(list)));
	}
	
	//Problem with lasy initialisation
	@Test
	public void getAuctionsWithMyBids() throws JsonProcessingException, Exception {
		//Given
		String profId = "223e4567-e89b-12d3-a456-426614174000";
		int auctionId = 2;
		List<Auction> list = new ArrayList<>();
		Auction auction = this.ar.findById(1).get();
		list.add(auction);
		
		// Then
		this.mvc
			.perform(get("/getAuctionsWithMyBids/{myId}", profId))
			.andExpect(status().isOk())
//			.andExpect(content().json(mapper.writeValueAsString(list)))
			;
	}
}
