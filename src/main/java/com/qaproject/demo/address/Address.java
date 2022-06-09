package com.qaproject.demo.address;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qaproject.demo.auctions.Auction;
import com.qaproject.demo.clients.Consumer;

@Table(name = "address")
@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int id;
	
	@OneToOne(mappedBy = "address")
	@JsonIgnore
	private Consumer client;
	
	@OneToOne(mappedBy = "address")
	@JsonIgnore
	private Auction auction;
	
	private String label; //= "Invalidenstraße 117, 10115 Berlin, Deutschland"
	
    private String country_code; //DEU
    private String country_name; //Deutschland
    private String state_code; //BE",
    private String state; //Berlin",
    private String county_code; //B",
    private String county; //Berlin",
    private String city; //Berlin",
    private String district; //Mitte",
    private String street; //Invalidenstraße",
    private String postal_code; //10115",
    private String house_number; //117"

	private double lat;
	private double lng;
	
	public Address() {};
	
	public Address(double lat, double lng) {
		this.lat = lat;
		this.lng = lng;
	}
	
	public Address(String houseNumber, String street, String postalCode, String city) {
		this.house_number = houseNumber;
		this.street = street;
		this.postal_code = postalCode;
		this.city = city;
		this.label = street + " " + houseNumber + ", " + postalCode + " " + city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Consumer getClient() {
		return client;
	}

	public void setClient(Consumer client) {
		this.client = client;
	}

	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public String getState_code() {
		return state_code;
	}

	public void setState_code(String state_code) {
		this.state_code = state_code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCounty_code() {
		return county_code;
	}

	public void setCounty_code(String county_code) {
		this.county_code = county_code;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getHouse_number() {
		return house_number;
	}

	public void setHouse_number(String house_number) {
		this.house_number = house_number;
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

	@Override
	public String toString() {
		return "Address [id=" + id + ", client=" + client + ", auction=" + auction + ", label=" + label
				+ ", country_code=" + country_code + ", country_name=" + country_name + ", state_code=" + state_code
				+ ", state=" + state + ", county_code=" + county_code + ", county=" + county + ", city=" + city
				+ ", district=" + district + ", street=" + street + ", postal_code=" + postal_code + ", house_number="
				+ house_number + ", lat=" + lat + ", lng=" + lng + "]";
	}
	
	
	
}
