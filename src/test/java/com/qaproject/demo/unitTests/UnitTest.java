package com.qaproject.demo.unitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Objects;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.qaproject.demo.address.Address;
import com.qaproject.demo.auctions.Auction;
import com.qaproject.demo.auctions.Bid;
import com.qaproject.demo.clients.Consumer;
import com.qaproject.demo.clients.Professional;

public class UnitTest {
	@Test
	public void equal() {
		String consId = UUID.randomUUID().toString();
		Consumer con = new Consumer(consId);
		assertFalse("testing equals obj vs null", con.equals(null));
	}
	
	@Test
	public void ConsumerhashCodeTest() {
		String consId = UUID.randomUUID().toString();
		Consumer con = new Consumer(consId);
		int myhash = con.hashCode();
		int hash = Objects.hash("dupa");
		assertNotEquals("testing Consumer Hashcode", hash, myhash);
	}
	
	@Test
	public void BidhashCodeTest() {
		String consId = UUID.randomUUID().toString();
		Consumer con = new Consumer(consId);
		Auction auction = new Auction(con);
		int hash = Objects.hash(auction, 1, 1000f, 1);
		int myhash = con.hashCode();
		assertNotEquals("testing Bid Hashcode", hash, myhash);
	}
	
	@Test
	public void getWhoBid() {
		String consId = UUID.randomUUID().toString();
		String profId = UUID.randomUUID().toString();
		Consumer con = new Consumer(consId);
		Professional prof1 = new Professional(profId);
		Auction auction = new Auction(con);
		Bid bid = new Bid(1000f, prof1, auction);
		assertEquals("get Professional who made a bid in bid", prof1, bid.getWhoBid());
	}
	
	@Test
	public void createAddressWithLatLng() {
		double lat = 2.22222;
		double lng = 1.11111;
		Address myAddress = new Address(lat, lng);
		assertNotNull(myAddress);
	}
	
	@Test
	public void addressConstructor_andGetters_andSetters() {
		Address address = new Address("1a", "Hardwick Road East", "S802NS", "Worksop");
		address.setId(0);
		assertThat(address.getId()).isEqualTo(0);
		assertThat(address.getHouse_number()).isEqualTo("1a");
		assertThat(address.getStreet()).isEqualTo("Hardwick Road East");
		assertThat(address.getPostal_code()).isEqualTo("S802NS");
		assertThat(address.getCity()).isEqualTo("Worksop");
	}
}
