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
import com.qaproject.demo.clients.Consumer;
import com.qaproject.demo.repositories.AuctionRepo;
import com.qaproject.demo.repositories.ConsumerRepo;

@SpringBootTest
public class ServiceTestAuction {
	
	@Autowired
	private AuctionService as;
	@MockBean
	private AuctionRepo ar;
	@MockBean
	private ConsumerRepo cr;
	
	@Test
	public void getAuctions() {
		//Given
		int clientId = 1;
		Auction auction1 = new Auction();
		auction1.setId(1);
		Auction auction2 = new Auction();
		auction2.setId(2);
		List<Auction> list = new ArrayList<>();
		list.add(auction1);
		list.add(auction2);
		//When
		Mockito.when(this.ar.findAllAuctionByWhoCreated(clientId)).thenReturn(list);
		//Then
		assertThat(this.as.getAuctions(clientId)).isEqualTo(list);
		//Verify
		Mockito.verify(this.ar, Mockito.times(1)).findAllAuctionByWhoCreated(Mockito.anyInt());
	}

	@Test
	public void createAuction() {
		//Given 
		int customerId = 1;
		Consumer consumer = new Consumer(customerId);
		Auction auction = new Auction(consumer);
		//When
		Mockito.when(this.cr.findById(customerId)).thenReturn(Optional.of(consumer));
		Mockito.when(this.ar.save(auction)).thenReturn(auction);
		//Than
		assertThat(this.as.createAuction(customerId)).isEqualTo(auction);
		//Verify
		Mockito.verify(this.ar, Mockito.times(1)).save(Mockito.any(Auction.class));
		Mockito.verify(this.cr, Mockito.times(1)).findById(Mockito.anyInt());
	}
}
