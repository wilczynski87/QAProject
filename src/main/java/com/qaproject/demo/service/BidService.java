package com.qaproject.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	
	public Bid makeBid(Bid myBid, String profId, Integer auctionId) {
		Professional prof = Optional.ofNullable(this.pr.getProfessionalById(profId))
				.orElseThrow(() -> new NoClientFound("No proffesional exist"));
		
		Auction auc = ar.findById(auctionId)
				.orElseThrow(() -> new NoAuctionFound("No auction exist"));
		
		myBid.setAuction(auc);
		myBid.setWhoBid(prof);
		
		return Optional.of(this.br.save(myBid))
				.orElseThrow(() -> new NoBidFound("Could not SAVE the Bid"));
	}
	
	public List<Bid> getBidsByAuctionId(Integer auctionId) {
		return this.br.findAllBidByAuctionId(auctionId);
	}

	public List<Auction> getAuctionsWithMyBids(String myId) {
		List<String> auctionsId = Optional.ofNullable(
				this.br.findAllAuctionsWithMyBid(myId)).orElseThrow(() -> new NoBidFound("Could not found any bids :-(")
		);
		
		return auctionsId
				.stream()
				.map(id -> Integer.valueOf(id))
				.map(id -> this.ar.findById(id))
				.map(opt -> opt.get())
				.collect(Collectors.toList());
	}
	
	public Boolean deleteBid(Integer bidId) {
		if(this.br.existsById(bidId)) {
			this.br.deleteById(bidId);
			return this.br.existsById(bidId);
		} else throw new NoBidFound("Could not found bid with ID " + bidId);
	}
}
