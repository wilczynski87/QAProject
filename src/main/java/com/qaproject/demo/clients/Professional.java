package com.qaproject.demo.clients;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.qaproject.demo.auctions.Bid;

import lombok.Data;

@Table(name = "professional")
@Entity
@Data
public class Professional extends Consumer {
	
	@SuppressWarnings("unused") //because lombook doing the job
	private String licenseNo;
	
	@OneToMany(mappedBy = "whoBid", cascade = CascadeType.ALL)
	private List<Bid> bidList;
	
	public Professional() {
		super();
	}
	
	//for unit tests
	public Professional(String id) {
		super(id);
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

}
