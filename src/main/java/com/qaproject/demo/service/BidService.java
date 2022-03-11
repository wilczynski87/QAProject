package com.qaproject.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qaproject.demo.auctions.Auction;
import com.qaproject.demo.auctions.Bid;
import com.qaproject.demo.clients.Professional;
import com.qaproject.demo.exceptions.NoAuctionFound;
import com.qaproject.demo.exceptions.NoBidFound;
import com.qaproject.demo.exceptions.NoClientFound;
import com.qaproject.demo.repositories.AuctionRepo;
import com.qaproject.demo.repositories.BidRepo;
import com.qaproject.demo.repositories.ProfessionalRepo;

@Service
public class BidService {
	private BidRepo br;
	private ProfessionalRepo pr;
	private AuctionRepo ar;

	@Autowired
	public BidService(BidRepo br, ProfessionalRepo pr, AuctionRepo ar) {
		this.br = br;
		this.pr = pr;
		this.ar = ar;
	}
	
	public Bid makeBid(Bid myBid, Integer professionalId, Integer auctionId) {
		Professional prof = pr.findById(professionalId).orElseThrow(() -> new NoClientFound("No proffesional exist"));
		Auction auc = ar.findById(auctionId).orElseThrow(() -> new NoAuctionFound("No auction exist"));
		float price = Optional.of(myBid.getPrice()).orElseThrow();
		Bid createdBid = Optional.of(new Bid(price, prof, auc)).orElseThrow(() -> new NoBidFound("Could not CREATE a Bid")) ;
		
		return Optional.of(this.br.save(createdBid)).orElseThrow(() -> new NoBidFound("Could not SAVE the Bid"));
	}
	
	public List<Bid> getBidsByAuctionId(Integer auctionId) {
		return this.br.findAllBidByAuctionId(auctionId);
	}
	
}
