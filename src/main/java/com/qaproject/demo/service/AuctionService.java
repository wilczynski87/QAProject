package com.qaproject.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qaproject.demo.auctions.Auction;
import com.qaproject.demo.clients.Consumer;
import com.qaproject.demo.exceptions.NoAuctionFound;
import com.qaproject.demo.exceptions.NoClientFound;
import com.qaproject.demo.filters.Filters;
import com.qaproject.demo.repositories.AuctionRepo;
import com.qaproject.demo.repositories.ConsumerRepo;
import com.qaproject.demo.repositories.ProfessionalRepo;

@Service
public class AuctionService {
	
	private AuctionRepo ar;
	private ConsumerRepo cr;
	private ProfessionalRepo pr;
	
	@Autowired
	public AuctionService(AuctionRepo ar, ConsumerRepo cr, ProfessionalRepo pr) {
		this.ar = ar;
		this.cr = cr;
		this.pr = pr;
	}
	
	public Auction createAuction(Integer customerId, Auction newAuction) {
		Consumer clientFound = this.cr.findById(customerId).orElseThrow(() -> new NoClientFound("There are no such Client, to create an Auction"));
		newAuction.setWhoCreated(clientFound);
		return Optional.of(this.ar.save(newAuction)).orElseThrow(() -> new NoAuctionFound("Could not create an auction"));
	}
	
	public List<Auction> getAuctions(Integer clientId) {
		return Optional.of(this.ar.findAllAuctionByWhoCreated(clientId)).get();
	}
	
//	public Boolean deleteAuction(Integer id) {
//		if(Optional.ofNullable(this.ar.findById(id)).isPresent()) {
//			this.ar.deleteById(id);
//			return true;
//		} else return false; 
//	}
	public Boolean deleteAuction(Integer id) {
		if(Optional.ofNullable(this.ar.findById(id)).isPresent()) {
			this.ar.deleteById(id);
			return Optional.ofNullable(this.ar.findById(id)).isEmpty();
		} return true;
		
	}
	
	public List<Auction> getAuctionFiltered(Integer professionalId, Filters body) {
		Optional.ofNullable(this.pr.findById(professionalId)).orElseThrow();
		
		List<Auction> auctions = this.ar.findAll()
				.stream()
//				.filter(auction -> auction.getVolume() > body.getVolume())
				.collect(Collectors.toList());
		
		return auctions;
	}
	
}
