package com.qaproject.demo.clients;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.qaproject.demo.auctions.Auction;

import lombok.Data;

@Table(name = "consumer")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@Entity
public class Consumer extends Client {

	@OneToMany(mappedBy = "whoCreated", cascade = CascadeType.ALL)
	private List<Auction> listOfAuction;
	
	public Consumer() {
		super();
	}
	
	public Consumer(String id) {
		super(id);
	}
	
}
