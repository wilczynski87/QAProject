package com.qaproject.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
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
import com.qaproject.demo.filters.Filters;
import com.qaproject.demo.repositories.AuctionRepo;
import com.qaproject.demo.repositories.ConsumerRepo;
import com.qaproject.demo.repositories.ProfessionalRepo;

@SpringBootTest
public class ServiceTestAuction {
	
	@Autowired
	private AuctionService as;
	
	@MockBean
	private AuctionRepo ar;
	
	@MockBean
	private ProfessionalRepo pr;
	
	@MockBean
	private ConsumerRepo cr;
	
	@Test
	public void getAuctions() {
		//Given
		String clientId = UUID.randomUUID().toString();
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
		Mockito.verify(this.ar, Mockito.times(1)).findAllAuctionByWhoCreated(clientId);
	}

	@Test
	public void createAuction() {
		//Given 
//		int customerId = 1;
		String customerId = UUID.randomUUID().toString();
		Consumer consumer = new Consumer(customerId);
		Auction auction = new Auction(consumer);
		//When
		Mockito.when(this.cr.getConsumerById(customerId)).thenReturn(consumer);
		Mockito.when(this.ar.save(auction)).thenReturn(auction);
		//Than
		assertThat(this.as.createAuction(customerId, auction)).isEqualTo(auction);
		//Verify
		Mockito.verify(this.ar, Mockito.times(1)).save(Mockito.any(Auction.class));
		Mockito.verify(this.cr, Mockito.times(1)).getConsumerById(customerId);
	}
	
	@Test
	public void deleteAuction() {
		//Given
		Auction auction1 = new Auction();
		auction1.setId(1);
		when(this.ar.findById(1)).thenReturn(Optional.ofNullable(auction1));
		//Than
		assertThat(this.as.deleteAuction(1)).isFalse();
	}
	
	@Test 
	public void expireAuction() {
		//Given
		Auction auction1 = new Auction();
		auction1.setId(1);
		auction1.setExpired(false);
		Auction auction2 = new Auction();
		auction1.setId(1);
		auction2.setExpired(true);
		when(this.ar.findById(1)).thenReturn(Optional.ofNullable(auction1));
		when(this.ar.save(auction1)).thenReturn(auction2);
		//Than
		assertThat(this.as.expireAuction(1)).isEqualTo(auction2);
	}
	
	@Test
	public void getAuctionFiltered_returnWood_whenFillterWood() {
		//Given
		// making prof
			String professionalId = UUID.randomUUID().toString();
			Professional professional = new Professional(professionalId);
			professional.setAddress(new Address(1.0000, 1.0000));
		// making filter
			Filters filter = new Filters("Wood", 100, "01012022", "01022022", "01012022",
					10, 1, 10000, false);
		// making auction list
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
			
		
		//When
		when(this.pr.getProfessionalById(professionalId)).thenReturn(professional);
		when(this.ar.findAll()).thenReturn(auctionList);

		//Than
		System.out.println(this.as.getAuctionFiltered(professionalId, filter).toString());
		assert(this.as.getAuctionFiltered(professionalId, filter)).contains(auction2);

		//Verify
		
	}
	
	@Test
	public void expiredAuctions_returnAuction1_whenOthersAreExpired() {
		//Given
		// making prof
			String professionalId = UUID.randomUUID().toString();
			Professional professional = new Professional(professionalId);
			professional.setAddress(new Address(1.0000, 1.0000));
		// making filter
			Filters filter = new Filters("Wood", 100, "01012022", "01022022", "01012022",
					10, 1, 10000, false);
			filter.setTitle("Junk");
		// making auction list
			List<Auction> auctionList = new ArrayList<>();
			String consId = UUID.randomUUID().toString();
			Consumer consumer = new Consumer(consId);
			List<Bid> bidList = new ArrayList<>();
			Auction auction1 = new Auction(0, consumer, bidList, new Address(1.0001, 1.0001),"01012022", "my Junk",
					"Mix", 2, "5", 1, "01012022", "01012022", "no note", false);
			Auction auction2 = new Auction(0, consumer, bidList, new Address(1.0001, 1.0002),"01012022", "my Junk furniture",
					"Wood", 2, "5", 1, "01012022", "01012022", "no note", false);
			Auction auction3 = new Auction(0, consumer, bidList, new Address(1.0001, 1.0003),"01012022", "my Junk plastic",
					"Plastic", 2, "5", 1, "01012022", "01012022", "no note", false);
			auctionList.add(auction1);
			auctionList.add(auction2);
			auctionList.add(auction3);
			
		
		//When
		when(this.pr.getProfessionalById(professionalId)).thenReturn(professional);
		when(this.ar.findAll()).thenReturn(auctionList);

		//Than
		System.out.println(this.as.getAuctionFiltered(professionalId, filter).toString());
		assert(this.as.expiredAuctions(professionalId, filter)).contains(auction2);
	}
	
}
