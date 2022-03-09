package com.qaproject.demo.auctions;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.qaproject.demo.clients.Consumer;
import com.qaproject.demo.clients.Professional;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Bid {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private float price;
	
	@ManyToOne(targetEntity = Professional.class)
	@JoinColumn(name = "f_key_Professional_id")
	private Professional whoBid;
	
	@ManyToOne(targetEntity = Auction.class)
	@JoinColumn(name = "Auction_id")
	private Auction auction;

	public Bid() {
		super();
	}

	public Bid(float price, Professional whoBid, Auction auction) {
		this.price = price;
		this.whoBid = whoBid;
		this.auction = auction;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Professional getWhoBid() {
		return whoBid;
	}

	public void setWhoBid(Professional whoBid) {
		this.whoBid = whoBid;
	}

	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	public int getId() {
		return id;
	}
	
}
