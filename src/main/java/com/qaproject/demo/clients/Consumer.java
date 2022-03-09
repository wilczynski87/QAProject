package com.qaproject.demo.clients;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.qaproject.demo.auctions.Auction;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Consumer extends Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(mappedBy = "whoCreated", cascade = CascadeType.ALL)
	private List<Auction> listOfAuction;
	
	public Consumer() {
		super();
	}
	
	public int getId() {
		return this.id;
	}
	
}
