package com.qaproject.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qaproject.demo.auctions.Auction;
import com.qaproject.demo.auctions.Bid;

@Repository
public interface BidRepo extends JpaRepository<Bid, Integer> {
	
	@Query(value = "SELECT * FROM bid WHERE Auction_id = ?1", nativeQuery = true)
	public List<Bid> findAllBidByAuctionId(Integer auctionId);
	
	@Query(value = "SELECT *\n"
			+ "FROM auction\n"
			+ "INNER JOIN bid ON auction.id=bid.auction_id\n"
			+ "WHERE bid.f_key_professional_id = ?1;", nativeQuery = true)
	public List<Auction> findAuctionsByBidOwner(String myId);
	
	@Query(value = "SELECT auction_id FROM bid WHERE f_key_professional_id = ?1 GROUP BY auction_id", nativeQuery = true)
	public List<String> findAllAuctionsWithMyBid(String myId);
	
	public List<Bid> findAllBidByProfFirm(String myId);

}
