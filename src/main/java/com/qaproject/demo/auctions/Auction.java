package com.qaproject.demo.auctions;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.qaproject.demo.clients.Consumer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Auction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(targetEntity = Consumer.class)
	@JoinColumn(name = "f_key_Consumer_id")
	private Consumer whoCreated;
	
	@OneToMany(mappedBy = "auction")
	private List<Bid> bids; 
	
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
	
	
}
