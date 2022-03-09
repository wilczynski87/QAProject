package com.qaproject.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qaproject.demo.auctions.Bid;

@Repository
public interface BidRepo extends JpaRepository<Bid, Integer> {
	
	@Query(value = "SELECT * FROM bid WHERE Auction_id = ?1", nativeQuery = true)
	public List<Bid> findAllBidByAuctionId(Integer auctionId);

}
