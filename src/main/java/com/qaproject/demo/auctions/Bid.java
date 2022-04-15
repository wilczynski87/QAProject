package com.qaproject.demo.auctions;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qaproject.demo.clients.Professional;

@Entity
public class Bid {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Float price;
	private String date;
	
	@ManyToOne(targetEntity = Professional.class)
	@JoinColumn(name = "f_key_Professional_id")
	@JsonIgnore
	private Professional whoBid;
	
	@ManyToOne(targetEntity = Auction.class)
	@JoinColumn(name = "auction_id")
	@JsonIgnore
	private Auction auction;

	public Bid() {
		super();
	}

	public Bid(float price, Professional whoBid, Auction auction) {
		this.price = price;
		this.whoBid = whoBid;
		this.auction = auction;
		this.date = "01/01/2022";
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Bid [id=" + id + ", price=" + price + ", whoBid=" + whoBid + ", auction=" + auction + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(auction, id, price, whoBid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bid other = (Bid) obj;
		return Objects.equals(auction, other.auction) && id == other.id
				&& Float.floatToIntBits(price) == Float.floatToIntBits(other.price)
				&& Objects.equals(whoBid, other.whoBid);
	}
	
}
