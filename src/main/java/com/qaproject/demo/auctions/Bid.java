package com.qaproject.demo.auctions;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qaproject.demo.clients.Professional;

@Table(name = "bid")
@Entity
public class Bid {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Float price;
	private String date;
	private String profFirm;
	private int howManyDays;
	private String startDate;
	private String endDate;
	
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

	public String getProfFirm() {
		return profFirm;
	}

	public void setProfFirm(String profFirm) {
		this.profFirm = profFirm;
	}

	public int getHowManyDays() {
		return howManyDays;
	}

	public void setHowManyDays(int howManyDays) {
		this.howManyDays = howManyDays;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
	public int hashCode() {
		return Objects.hash(auction, date, endDate, howManyDays, id, price, profFirm, startDate, whoBid);
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
		return Objects.equals(auction, other.auction) && Objects.equals(date, other.date)
				&& Objects.equals(endDate, other.endDate) && howManyDays == other.howManyDays && id == other.id
				&& Objects.equals(price, other.price) && Objects.equals(profFirm, other.profFirm)
				&& Objects.equals(startDate, other.startDate) && Objects.equals(whoBid, other.whoBid);
	}

	@Override
	public String toString() {
		return "Bid [id=" + id + ", price=" + price + ", date=" + date + ", profFirm=" + profFirm + ", howManyDays="
				+ howManyDays + ", startDate=" + startDate + ", endDate=" + endDate + ", whoBid=" + whoBid
				+ ", auction=" + auction + "]";
	}
	
}
