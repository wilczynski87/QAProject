package com.qaproject.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qaproject.demo.auctions.Auction;
import com.qaproject.demo.auctions.Bid;
import com.qaproject.demo.clients.Professional;
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
		int professionalId = 1;
		Professional pro = new Professional(professionalId);
		Auction auc = new Auction();
		auc.setId(auctionId);
		float price = 100f;
		Bid bid = new Bid();
		bid.setAuction(auc);
		bid.setPrice(price);
		bid.setWhoBid(pro);
		System.out.println(bid.toString());
		Optional<Professional> proO = Optional.of(pro);
		Optional<Auction> aucO = Optional.of(auc);
		//When
		Mockito.when(this.br.save(bid)).thenReturn(bid);
		Mockito.when(this.pr.findById(professionalId)).thenReturn(proO);
		Mockito.when(this.ar.findById(auctionId)).thenReturn(aucO);
		//Then
		assertThat(this.pr.findById(professionalId)).isEqualTo(proO);
		assertThat(this.ar.findById(auctionId)).isEqualTo(aucO);
		assertThat(this.bs.makeBid(bid, professionalId, auctionId)).isEqualTo(bid);
		//Verify
		Mockito.verify(this.br, Mockito.times(1)).save(Mockito.any(Bid.class));
	}
	
	@Test 
	public void getBidsByAuctionId() {
		//Given
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
		//When
		Mockito.when(this.br.findAllBidByAuctionId(auctionId)).thenReturn(list);
		//Than
		assertThat(this.bs.getBidsByAuctionId(auctionId)).isEqualTo(list);
		//Verify
		Mockito.verify(this.br, Mockito.times(1)).findAllBidByAuctionId(Mockito.anyInt());
	}
}
