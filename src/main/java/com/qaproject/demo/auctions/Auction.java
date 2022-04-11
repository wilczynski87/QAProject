package com.qaproject.demo.auctions;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.qaproject.demo.clients.Consumer;

@Entity
public class Auction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(targetEntity = Consumer.class)
	@JoinColumn(name = "f_key_consumer_id")
	private Consumer whoCreated;
	
	@OneToMany(mappedBy = "auction")
	private List<Bid> bids; 
	
	private String auctionStart;
	private String title;
	private String junkType;
	private int volume;
	private String containerType;
	private int containerNumber;
	private String startDate;
	private String endDate;
	private String address;
	private String note;
	
	public Auction() {
		super();
	}
	
	public Auction(Consumer consumer) {
		this.whoCreated = consumer;
	}

	public int getWhoCreated() {
		return this.whoCreated.getId();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Bid> getBids() {
		return bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}

	public void setWhoCreated(Consumer whoCreated) {
		this.whoCreated = whoCreated;
	}
	public String getAuctionStart() {
		return auctionStart;
	}

	public void setAuctionStart(String auctionStart) {
		this.auctionStart = auctionStart;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getJunkType() {
		return junkType;
	}

	public void setJunkType(String junkType) {
		this.junkType = junkType;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String getContainerType() {
		return containerType;
	}

	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}

	public int getContainerNumber() {
		return containerNumber;
	}

	public void setContainerNumber(int containerNumber) {
		this.containerNumber = containerNumber;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bids, id, whoCreated);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Auction other = (Auction) obj;
		return Objects.equals(bids, other.bids) && id == other.id && Objects.equals(whoCreated, other.whoCreated);
	}
	
	
}
