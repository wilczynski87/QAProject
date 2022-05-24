package com.qaproject.demo.auctions;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.qaproject.demo.address.Address;
import com.qaproject.demo.clients.Consumer;

@Table(name = "auction")
@Entity
public class Auction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//check if I can move it to Client class
	@ManyToOne(targetEntity = Consumer.class)
	@JoinColumn(name = "f_key_consumer_id")
	private Consumer whoCreated;
	
	@OneToMany(mappedBy = "auction")
	private List<Bid> bids; 
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "auction_address")
	private Address address;
	
	private String auctionStart;
	private String title;
	private String junkType;
	private int volume;
	private String containerType;
	private int containerNumber;
	private String startDate;
	private String endDate;
	private String note;
	
	public Auction() {
		super();
	}
	
	public Auction(Consumer consumer) {
		this.whoCreated = consumer;
	}

	public String getWhoCreated() {
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
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
		return Objects.hash(auctionStart, bids, containerNumber, containerType, endDate, id, junkType, note, startDate,
				title, volume, whoCreated);
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
		return Objects.equals(auctionStart, other.auctionStart) && Objects.equals(bids, other.bids)
				&& containerNumber == other.containerNumber && Objects.equals(containerType, other.containerType)
				&& Objects.equals(endDate, other.endDate) && id == other.id && Objects.equals(junkType, other.junkType)
				&& Objects.equals(note, other.note) && Objects.equals(startDate, other.startDate)
				&& Objects.equals(title, other.title) && volume == other.volume
				&& Objects.equals(whoCreated, other.whoCreated);
	}

	@Override
	public String toString() {
		return "Auction [id=" + id + ", whoCreated=" + whoCreated + ", bids=" + bids + ", auctionStart=" + auctionStart
				+ ", title=" + title + ", junkType=" + junkType + ", volume=" + volume + ", containerType="
				+ containerType + ", containerNumber=" + containerNumber + ", startDate=" + startDate + ", endDate="
				+ endDate + ", note=" + note + "]";
	}
		
}
