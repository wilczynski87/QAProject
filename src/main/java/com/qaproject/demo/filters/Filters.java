package com.qaproject.demo.filters;

import java.util.Objects;

public class Filters {
	
	private String junkType;
	private int distance;
	private String startDate;
	private String endDate;
	private String auctionStarted;
	private int volume;
	private float lowestBid;
	
	public Filters() {};
	
	public Filters(String junkType, int distance, String startDate, String endDate, String auctionStarted, int volume,
			float lowestBid) {
		super();
		this.junkType = junkType;
		this.distance = distance;
		this.startDate = startDate;
		this.endDate = endDate;
		this.auctionStarted = auctionStarted;
		this.volume = volume;
		this.lowestBid = lowestBid;
	}

	public String getJunkType() {
		return junkType;
	}

	public void setJunkType(String junkType) {
		this.junkType = junkType;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getAuctionStarted() {
		return auctionStarted;
	}

	public void setAuctionStarted(String auctionStarted) {
		this.auctionStarted = auctionStarted;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public float getLowestBid() {
		return lowestBid;
	}

	public void setLowestBid(float lowestBid) {
		this.lowestBid = lowestBid;
	}

	@Override
	public int hashCode() {
		return Objects.hash(auctionStarted, distance, endDate, junkType, lowestBid, startDate, volume);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filters other = (Filters) obj;
		return Objects.equals(auctionStarted, other.auctionStarted) && distance == other.distance
				&& Objects.equals(endDate, other.endDate) && Objects.equals(junkType, other.junkType)
				&& Float.floatToIntBits(lowestBid) == Float.floatToIntBits(other.lowestBid)
				&& Objects.equals(startDate, other.startDate) && volume == other.volume;
	}

	@Override
	public String toString() {
		return "Filters [junkType=" + junkType + ", distance=" + distance + ", startDate=" + startDate + ", endDate="
				+ endDate + ", auctionStarted=" + auctionStarted + ", volume=" + volume + ", lowestBid=" + lowestBid
				+ "]";
	};
	
}
