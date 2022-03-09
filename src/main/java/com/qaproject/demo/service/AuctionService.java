package com.qaproject.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qaproject.demo.auctions.Auction;
import com.qaproject.demo.clients.Consumer;
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
		if(Optional.of(customerId).isPresent()) {
			int cid = customerId;
			if(this.cr.findById(cid).isPresent()) {
				Consumer clientFound = this.cr.findById(cid).get();
				Auction auction = new Auction(clientFound);
				return this.ar.save(auction);
			} else throw new NoClientFound("There are no such Client, to create an Auction");
		} else throw new NullPointerException("There is no Client ID");
	}
	
	public List<Auction> getAuctions(Integer id) {
		return this.ar.findAllAuctionByWhoCreated(id);
	}
	
}
