package com.qaproject.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import com.qaproject.demo.auctions.Auction;
import com.qaproject.demo.auctions.Bid;
import com.qaproject.demo.clients.Consumer;
import com.qaproject.demo.clients.Professional;

public class UnitTest {
	@Test
	public void equal() {
		Consumer con = new Consumer(1);
		assertFalse("testing equals obj vs null", con.equals(null));
	}
	
	@Test
	public void ConsumerhashCodeTest() {
		Consumer con = new Consumer(1);
		int myhash = con.hashCode();
		int hash = Objects.hash("dupa");
		assertNotEquals("testing Consumer Hashcode", hash, myhash);
	}
	
	@Test
	public void BidhashCodeTest() {
		Consumer con = new Consumer(1);
		Auction auction = new Auction(con);
		int hash = Objects.hash(auction, 1, 1000f, 1);
		int myhash = con.hashCode();
		assertNotEquals("testing Bid Hashcode", hash, myhash);
	}
	
	@Test
	public void getWhoBid() {
		Consumer con = new Consumer(1);
		Professional prof1 = new Professional(1);
		Auction auction = new Auction(con);
		Bid bid = new Bid(1000f, prof1, auction);
		assertEquals("get Professional who made a bid in bid", prof1, bid.getWhoBid());
	}
}
