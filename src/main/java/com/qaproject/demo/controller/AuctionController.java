package com.qaproject.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.qaproject.demo.auctions.Auction;
import com.qaproject.demo.service.AuctionService;

@Controller
public class AuctionController {
	private AuctionService as;

	@Autowired
	public AuctionController(AuctionService as) {
		this.as = as;
	}
	
	@PostMapping("/createAuction/{customerId}")
	public ResponseEntity<Auction> createAuction(@PathVariable("customerId") Integer cunstomerId) {
		return new ResponseEntity<Auction> (this.as.createAuction(cunstomerId), HttpStatus.CREATED);
	}
	
	@GetMapping("/getAuctionsByConsumerId/{id}")
	public ResponseEntity<List<Auction>> getAuctions(@PathVariable Integer id) {
		return new ResponseEntity<List<Auction>> (this.as.getAuctions(id), HttpStatus.ACCEPTED);
	}
	
}
