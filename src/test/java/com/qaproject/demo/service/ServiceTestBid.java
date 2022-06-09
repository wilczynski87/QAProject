package com.qaproject.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qaproject.demo.address.Address;
import com.qaproject.demo.auctions.Auction;
import com.qaproject.demo.auctions.Bid;
import com.qaproject.demo.clients.Consumer;
import com.qaproject.demo.clients.Professional;
import com.qaproject.demo.exceptions.NoBidFound;
import com.qaproject.demo.repositories.AuctionRepo;
import com.qaproject.demo.repositories.BidRepo;
import com.qaproject.demo.repositories.ProfessionalRepo;

@SpringBootTest
public class ServiceTestBid {

	@Autowired
	BidService bs;
	
	@MockBean
	BidRepo br;
	@MockBean
	ProfessionalRepo pr;
	@MockBean
	AuctionRepo ar;
	
//	@Disabled
	@Test
	public void makeBid() {
		//Given
		int auctionId = 1;
		String profId = UUID.randomUUID().toString();
		Professional pro = new Professional(profId);
		Auction auc = new Auction();
		auc.setId(auctionId);
		float price = 100f;
		Bid bid = new Bid();
		bid.setAuction(auc);
		bid.setPrice(price);
		bid.setWhoBid(pro);
		System.out.println(bid.toString());
		Professional proO = Optional.of(pro).get();
		Optional<Auction> aucO = Optional.of(auc);
		//When
		Mockito.when(this.br.save(bid)).thenReturn(bid);
		Mockito.when(this.pr.getProfessionalById(profId)).thenReturn(proO);
		Mockito.when(this.ar.findById(auctionId)).thenReturn(aucO);
		//Then
		assertThat(this.pr.getProfessionalById(profId)).isEqualTo(proO);
		assertThat(this.ar.findById(auctionId)).isEqualTo(aucO);
		assertThat(this.bs.makeBid(bid, profId, auctionId)).isEqualTo(bid);
		//Verify
		Mockito.verify(this.br, Mockito.times(1)).save(Mockito.any(Bid.class));
	}
	
	@Test 
	public void getBidsByAuctionId() {
		// Given
		int auctionId = 1;
		Auction auction1 = new Auction();
		auction1.setId(auctionId);
		Bid bid1 = new Bid();
		bid1.setAuction(auction1);
		Bid bid2 = new Bid();
		bid2.setAuction(auction1);
		List<Bid> list = new ArrayList<>();
		list.add(bid1);
		list.add(bid2);
		// When
		Mockito.when(this.br.findAllBidByAuctionId(auctionId)).thenReturn(list);
		// Than
		assertThat(this.bs.getBidsByAuctionId(auctionId)).isEqualTo(list);
		// Verify
		Mockito.verify(this.br, Mockito.times(1)).findAllBidByAuctionId(Mockito.anyInt());
	}
	
	@Test
	public void getAuctionsWithMyBids() {
		// Given
		String myId = UUID.randomUUID().toString();
		Professional professional = new Professional(myId);
		Professional otherProfessional = new Professional();
		String consId = UUID.randomUUID().toString();
		Consumer consumer = new Consumer(consId);
		List<Bid> bidList = new ArrayList<>();
		List<Bid> otherBidList = new ArrayList<>();
		List<String> auctionList = new ArrayList<>();
		// creating auctions
		Auction auction1 = new Auction(1, consumer, bidList, new Address(1.0001, 1.0001),"01012022", "my Junk",
				"Mix", 2, "5", 1, "01012022", "01012022", "no note", false);
		Auction auction2 = new Auction(2, consumer, bidList, new Address(1.0001, 1.0002),"01012022", "my Junk furniture",
				"Wood", 2, "5", 1, "01012022", "01012022", "no note", false);
		Auction auction3 = new Auction(3, consumer, new ArrayList<Bid>(), new Address(1.0001, 1.0003),"01012022", "my Junk plastic",
				"Plastic", 2, "5", 1, "01012022", "01012022", "no note", false);
		//creating Bids
		bidList.add(new Bid(0, 1000f, "01012022", "Ltd1", 7, "01012022", "01012022", professional));
		bidList.add(new Bid(1, 1000f, "01012022", "Ltd1", 7, "01012022", "01012022", otherProfessional));
		otherBidList.add(new Bid(1, 1000f, "01012022", "Ltd1", 7, "01012022", "01012022", otherProfessional));
//		list.add(auction1);
//		list.add(auction2);
		auctionList.add("2");
		auctionList.add("1");
		
		List<Auction> result = new ArrayList<>();
		result.add(auction1);
		result.add(auction2);
		
		// When
		when(this.br.findAllAuctionsWithMyBid(myId)).thenReturn(auctionList);
		when(this.ar.findById(1)).thenReturn(Optional.ofNullable(auction1));
		when(this.ar.findById(2)).thenReturn(Optional.ofNullable(auction2));
		
		// Than
		assertThat(this.bs.getAuctionsWithMyBids(myId)).contains(auction1, auction2);
		
		// Verify
	
	}
	
	@Test
	public void deleteBid() {
		// Given
		int bidId = 0;
		
		// When
		when(this.br.existsById(bidId)).thenReturn(true);
		
		// When
		assertThat(this.bs.deleteBid(bidId)).isTrue();
	}
	
	@Test
	public void deleteBid_throwException_thenBidDoNotExist() {
		// Given
		int bidId = 0;
		
		// When
		when(this.br.existsById(bidId)).thenReturn(false);
		Exception exception = assertThrows(NoBidFound.class, () -> this.bs.deleteBid(bidId));
		
		// When
		String expectedMessage = "Could not found bid with ID " + bidId;
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
}
