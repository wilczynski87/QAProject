package com.qaproject.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.qaproject.demo.auctions.Auction;
import com.qaproject.demo.filters.Filters;
import com.qaproject.demo.service.AuctionService;

@CrossOrigin
@Controller
public class AuctionController {
	private AuctionService as;

	@Autowired
	public AuctionController(AuctionService as) {
		this.as = as;
	}
	
	@PostMapping("/createAuction/{customerId}")
	public ResponseEntity<Auction> createAuction(@PathVariable("customerId") String customerId, @RequestBody Auction newAuction) {
		return new ResponseEntity<Auction> (this.as.createAuction(customerId, newAuction), HttpStatus.CREATED);
	}
	
	@GetMapping("/getAuctionsByUserId/{id}")
	public ResponseEntity<List<Auction>> getAuctions(@PathVariable String id) {
		return new ResponseEntity<List<Auction>> (this.as.getAuctions(id), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteAuction/{id}")
	public ResponseEntity<Boolean> deleteAuction(@PathVariable Integer id) {
		return new ResponseEntity<Boolean> (this.as.deleteAuction(id), HttpStatus.OK);
	}
	
	@PostMapping("/getAuctionByFilers/{professionalId}")
	public ResponseEntity<List<Auction>> getAuctionFiltered(@PathVariable String professionalId, @RequestBody Filters body) {
		return new ResponseEntity<List<Auction>> (this.as.getAuctionFiltered(professionalId, body), HttpStatus.OK);
	}
	
}
