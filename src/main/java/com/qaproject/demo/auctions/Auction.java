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
}
