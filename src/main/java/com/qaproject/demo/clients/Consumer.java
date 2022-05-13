package com.qaproject.demo.clients;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.qaproject.demo.address.Address;
import com.qaproject.demo.auctions.Auction;

@Table(name = "consumer")
@Inheritance(
    strategy = InheritanceType.TABLE_PER_CLASS
)
@Entity
public class Consumer extends Client {

	@OneToMany(mappedBy = "whoCreated", cascade = CascadeType.ALL)
	private List<Auction> listOfAuction;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_address")
	private Address address;
	
	public Consumer() {
		super();
	}
	
	public Consumer(String id) {
		super(id);
	}
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
}
