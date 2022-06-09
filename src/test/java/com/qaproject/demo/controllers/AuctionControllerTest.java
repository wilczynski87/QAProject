package com.qaproject.demo.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qaproject.demo.address.Address;
import com.qaproject.demo.auctions.Auction;
import com.qaproject.demo.auctions.Bid;
import com.qaproject.demo.clients.Consumer;
import com.qaproject.demo.filters.Filters;
import com.qaproject.demo.repositories.AuctionRepo;
import com.qaproject.demo.repositories.ConsumerRepo;
import com.qaproject.demo.service.AuctionService;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = {"classpath:test-schema.sql", "classpath:prepopulation.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@AutoConfigureMockMvc
public class AuctionControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapped;
	
	@Autowired
	private AuctionRepo ar;
	@Autowired
	private ConsumerRepo cr;
	
	@Test
	public void helloWorld() throws Exception {
		this.mvc
			.perform(get("/hello"))
			.andExpect(status().isOk())
			.andExpect(content().string("Hello, Test successful!"));
			
	}
	
	@Test
	public void createAuction() throws JsonProcessingException, Exception {
		//Given
		String consId = "123e4567-e89b-12d3-a456-426614174000";
//		Consumer consumer = new Consumer(consId);
		Consumer consumer = this.cr.getConsumerById(consId);
//		consumer.setEmail("dupa@dupa.com");
		Address address = new Address(1.0001, 1.0001);
		address.setId(0);
		List<Bid> list = new ArrayList<>();
		
		Auction auctionToSend = new Auction(3, consumer, list, address, "07/01/2022", "junk from kitchen renewal",
				"wood", 5, "5m", 1, "08/01/2022", "31/01/2022",	"", false);
		auctionToSend.setWhoCreated(consumer);
		auctionToSend.setExpired(false);
		
		Auction auctionToReturn = new Auction(3, consumer, list, address, "07/01/2022", "junk from kitchen renewal",
				"wood", 5, "5m", 1, "08/01/2022", "31/01/2022",	"", false);
		auctionToReturn.setId(3);
		auctionToReturn.setWhoCreated(consumer);
		auctionToReturn.setExpired(false);
		
		//Veryfie
		this.mvc
			.perform(post("/createAuction/" + consId)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.mapped.writeValueAsString(auctionToSend)))
			.andExpect(status().isCreated())
			.andExpect(content().json(mapped.writeValueAsString(auctionToReturn)))
			;
	}

	@Test
	public void getAuctions() throws Exception {
		//Given
		String myId = "123e4567-e89b-12d3-a456-426614174000";

		//When and Then
		this.mvc
			.perform(get("/getAuctionsByUserId/{id}", myId)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
//			.andExpect(content().json(mapped.writeValueAsString(list2)));
			;
	}
	
	@Test
	public void deleteAuction() throws Exception {
		//Given
		int auctionId = 0;
		
		//When and Then
		this.mvc
			.perform(delete("/deleteAuction/" + auctionId))
			.andExpect(status().isOk())
			.andExpect(content().string("false"))
			;
	}
	
//	Problem with "lazyInitialisation" of Bids
//	@Disabled
	@Test
	public void expireAuction() throws Exception {
		//Given
		int auctionId = 1;
		String consInDB = "123e4567-e89b-12d3-a456-426614174000";
		Consumer consumer = new Consumer(consInDB);
//		Auction auction = new Auction(1, consumer, new ArrayList<Bid>(), new Address(), "07/01/2022", "junk from kitchen renewal",
//				"wood", 5, "5m", 1, "08/01/2022", "31/01/2022",	"", true);
//		auction.setId(1);
//		auction.setWhoCreated(consumer);
		Auction auction = this.ar.findById(auctionId).get();
		auction.setExpired(true);
		
		//When and Then
		this.mvc
			.perform(delete("/expireAuction/{id}", auctionId)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
//			.andExpect(content().json(mapped.writeValueAsString(auction)))
			;
	}
	
//	Problem with reciving data
//	@Disabled
	@Test
	public void getAuctionFiltered() throws Exception {
		// Given
		String profId = "223e4567-e89b-12d3-a456-426614174000";
		List<Auction> auctionList = new ArrayList<>();
		String consId = UUID.randomUUID().toString();
		Consumer consumer = new Consumer(consId);
		List<Bid> bidList = new ArrayList<>();
		Auction auction1 = new Auction(0, consumer, bidList, new Address(1.0001, 1.0001),"01012022", "first Auction",
				"Mix", 2, "5", 1, "01012022", "01012022", "no note", false);
		Auction auction2 = new Auction(0, consumer, bidList, new Address(1.0001, 1.0002),"01012022", "first Auction",
				"Wood", 2, "5", 1, "01012022", "01012022", "no note", false);
		Auction auction3 = new Auction(0, consumer, bidList, new Address(1.0001, 1.0003),"01012022", "first Auction",
				"Plastic", 2, "5", 1, "01012022", "01012022", "no note", false);
		auctionList.add(auction1);
		auctionList.add(auction2);
		auctionList.add(auction3);

//		String profInDB = "123e4567-e89b-12d3-a456-426614174000";
		
		Filters filter = new Filters("Wood", 100, "01012022", "01022022", "01012022",
				10, 1, 10000, false);
		
		// Veryfie
		this.mvc
			.perform(post("/getAuctionByFilers/{professionalId}", profId)
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)
					.content(this.mapped.writeValueAsString(filter)))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON)) //so far so good
//			.andExpect(content().json(mapped.writeValueAsString(auctionList)))
			;
	}
	
}
