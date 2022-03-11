package com.qaproject.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qaproject.demo.auctions.Auction;
import com.qaproject.demo.clients.Consumer;
import com.qaproject.demo.exceptions.NoAuctionFound;
import com.qaproject.demo.exceptions.NoClientFound;
import com.qaproject.demo.repositories.AuctionRepo;
import com.qaproject.demo.repositories.ConsumerRepo;

@Service
public class AuctionService {
	
	private AuctionRepo ar;
	private ConsumerRepo cr;
	
	@Autowired
	public AuctionService(AuctionRepo ar, ConsumerRepo cr) {
		this.ar = ar;
		this.cr = cr;
	}
	
	public Auction createAuction(Integer customerId) {
		Consumer clientFound = this.cr.findById(customerId).orElseThrow(() -> new NoClientFound("There are no such Client, to create an Auction"));
		Auction auction = new Auction(clientFound);
		return Optional.of(this.ar.save(auction)).orElseThrow(() -> new NoAuctionFound("Could not create an auction"));
	}
	
	public List<Auction> getAuctions(Integer clientId) {
		return Optional.of(this.ar.findAllAuctionByWhoCreated(clientId)).get();
	}
	
}
