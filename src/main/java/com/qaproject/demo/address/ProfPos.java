package com.qaproject.demo.address;

import java.util.Optional;

import com.qaproject.demo.clients.Professional;

public class ProfPos {
	
	private String name;
	private String email;
	private double lat;
	private double lng;
	private double distance; //distance from user
	private final double latMiles = 68.703;
	
	public ProfPos(String name, String email, double lat, double lng) {
		super();
		this.name = name;
		this.email = email;
		this.lat = lat;
		this.lng = lng;
		this.distance = 0;
	}
	
	public ProfPos(Professional prof) {
		super();
		this.name = Optional.ofNullable(prof.getFirm()).orElse(prof.getFullName());
		this.email = prof.getEmail();
		this.lat = prof.getAddress().getLat();
		this.lng = prof.getAddress().getLng();
		this.distance = 0;
	}
	
	public double calcDis(double consLat, double consLng) {
//		System.out.println("calcDis function working");
		double latDis = Math.abs(Math.abs(consLat) - Math.abs(this.lat));
		double lngDis = Math.abs(Math.abs(consLng) - Math.abs(this.lng));
		this.distance = Math.sqrt(Math.pow(latDis, 2) + Math.pow(lngDis, 2)) * latMiles;
		return this.distance;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
}
