package com.qaproject.demo.filters;

import java.util.Objects;

public class Filters {
	
	private String title;
	private String junkType;
	private double distanceMax;
	private String startDate;
	private String endDate;
	private String auctionStartedAfter;
	private int volumeMax = Integer.MAX_VALUE;
	private int volumeMin = Integer.MIN_VALUE;
	private float lowestBid;
	private boolean expired;
	
	public Filters() {}

	public Filters(String junkType, double distanceMax, String startDate, String endDate, String auctionStartedAfter,
			int volumeMax, int volumeMin, float lowestBid, boolean expired) {
		super();
		this.junkType = junkType;
		this.distanceMax = distanceMax;
		this.startDate = startDate;
		this.endDate = endDate;
		this.auctionStartedAfter = auctionStartedAfter;
		this.volumeMax = volumeMax;
		this.volumeMin = volumeMin;
		this.lowestBid = lowestBid;
		this.setExpired(expired);
	}

	public String getJunkType() {
		return junkType;
	}

	public void setJunkType(String junkType) {
		this.junkType = junkType;
	}

	public double getDistanceMax() {
		return distanceMax;
	}

	public void setDistanceMax(double distanceMax) {
		this.distanceMax = distanceMax;
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

	public String getAuctionStartedAfter() {
		return auctionStartedAfter;
	}

	public void setAuctionStartedAfter(String auctionStartedAfter) {
		this.auctionStartedAfter = auctionStartedAfter;
	}

	public int getVolumeMax() {
		return volumeMax;
	}

	public void setVolumeMax(int volumeMax) {
		this.volumeMax = volumeMax;
	}

	public int getVolumeMin() {
		return volumeMin;
	}

	public void setVolumeMin(int volumeMin) {
		this.volumeMin = volumeMin;
	}

	public float getLowestBid() {
		return lowestBid;
	}

	public void setLowestBid(float lowestBid) {
		this.lowestBid = lowestBid;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	};
	
	
}
