package com.qaproject.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qaproject.demo.address.ProfPos;
import com.qaproject.demo.auctions.Auction;
import com.qaproject.demo.auctions.Bid;
import com.qaproject.demo.clients.Consumer;
import com.qaproject.demo.clients.Professional;
import com.qaproject.demo.exceptions.NoAuctionFound;
import com.qaproject.demo.exceptions.NoClientFound;
import com.qaproject.demo.filters.Filters;
import com.qaproject.demo.repositories.AuctionRepo;
import com.qaproject.demo.repositories.BidRepo;
import com.qaproject.demo.repositories.ConsumerRepo;
import com.qaproject.demo.repositories.ProfessionalRepo;

@Service
public class AuctionService {
	
	private AuctionRepo ar;
	private ConsumerRepo cr;
	private ProfessionalRepo pr;
	private BidService bs;
	private BidRepo br;
	
	@Autowired
	public AuctionService(AuctionRepo ar, ConsumerRepo cr, ProfessionalRepo pr, BidService bs, BidRepo br) {
		this.ar = ar;
		this.cr = cr;
		this.pr = pr;
		this.bs = bs;
		this.br = br;
	}
	
	public Auction createAuction(String customerId, Auction newAuction) {
		Consumer clientFound = Optional.ofNullable(this.cr.getConsumerById(customerId)).orElseThrow(() -> new NoClientFound("There are no such Client, to create an Auction"));
		System.out.println("Clinet Found" + clientFound.getFullName());
		newAuction.setWhoCreated(clientFound);
		System.out.println(newAuction.getWhoCreated());
		return Optional.of(this.ar.save(newAuction)).orElseThrow(() -> new NoAuctionFound("Could not create an auction"));
	}
	
	public List<Auction> getAuctions(String clientId) {
		return Optional.of(this.ar.findAllAuctionByWhoCreated(clientId)).get();
	}
	
	//method to delete auction form Auction Table, together with Bids
	public Boolean deleteAuction(Integer id) {
		if(Optional.ofNullable(this.ar.findById(id)).isPresent()) {
			System.out.println("deleting auction started and present");
			List<Bid> bidList = this.bs.getBidsByAuctionId(id);
			if(bidList.size() > 0) this.bs.getBidsByAuctionId(id).forEach(bid -> this.bs.deleteBid(bid.getId()));
			this.ar.deleteById(id);
//			System.out.println(this.bs.getBidsByAuctionId(id));
			return Optional.ofNullable(this.ar.findById(id)).isEmpty();
		} return true;
		
	}
	
	public List<Auction> getAuctionFiltered(String professionalId, Filters filter) {
		Professional prof = Optional.ofNullable(this.pr.getProfessionalById(professionalId)).orElseThrow(() -> new NoClientFound("There are no such Professional") );
		
		List<Auction> auctions = this.ar.findAll()
				.stream()
				.filter(auction -> auction.getVolume() < filter.getVolumeMax())
				.filter(auction -> auction.getVolume() > filter.getVolumeMin())
				.filter(auction -> {
					float lowestBid = this.br.findLowestPriceInAuction(auction.getId()).orElse(10000f);
					return lowestBid >= filter.getLowestBid() ? true : false;
				})
				.filter(auction -> {
					if(Optional.ofNullable(prof.getAddress()).isEmpty() || Optional.ofNullable(auction.getAddress()).isEmpty()) {
						System.out.println("Can not find address of Professional or Auction");
						return false;
					} else {
						return ProfPos.getDistanceFromAuction(prof.getAddress(), auction.getAddress()) < filter.getDistanceMax();
					}
				})
				.collect(Collectors.toList());
		
		return auctions;
	}
	
}
