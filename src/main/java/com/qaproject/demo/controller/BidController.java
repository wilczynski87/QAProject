package com.qaproject.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.qaproject.demo.auctions.Bid;
import com.qaproject.demo.service.BidService;

@CrossOrigin
@Controller
public class BidController {
	
	private  BidService bs;
	
	@Autowired
	public BidController(BidService bs) {
		this.bs = bs;
	}
	
	@GetMapping("/getBids/{auctionId}")
	public ResponseEntity<List<Bid>> getBids(@PathVariable("auctionId") Integer auctionId) {
		return new ResponseEntity<List<Bid>> (this.bs.getBidsByAuctionId(auctionId), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/makeBid/{professionalId}/{auctionId}")
	public ResponseEntity<Bid> makeBid(@RequestBody Bid myBid, @PathVariable Integer professionalId, @PathVariable Integer auctionId) {
		return new ResponseEntity<Bid>	(this.bs.makeBid(myBid, professionalId, auctionId), HttpStatus.CREATED);
	}
}
