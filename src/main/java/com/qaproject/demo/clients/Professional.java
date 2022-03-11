package com.qaproject.demo.clients;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.qaproject.demo.auctions.Bid;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Professional extends Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String licenseNo;
	
	@OneToMany(mappedBy = "whoBid")
	private List<Bid> bidList;
	
	public Professional() {
		super();
	}
	
	//for unit tests
	public Professional(int id) {
		this.id = id;
	}

}
