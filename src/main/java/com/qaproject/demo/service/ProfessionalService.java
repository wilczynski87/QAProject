package com.qaproject.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qaproject.demo.address.ProfPos;
import com.qaproject.demo.auctions.Bid;
import com.qaproject.demo.clients.Professional;
import com.qaproject.demo.exceptions.ClientAlredyExist;
import com.qaproject.demo.exceptions.NoClientFound;
import com.qaproject.demo.repositories.BidRepo;
import com.qaproject.demo.repositories.ProfessionalRepo;

@Service
public class ProfessionalService {
	
	private ProfessionalRepo pr;
	private BidRepo br;
	
	@Autowired
	public ProfessionalService(ProfessionalRepo pr, BidRepo br) {
		this.pr = pr;
		this.br = br;
	}

	public Professional login(String email, String password) {
		Optional<Professional> login = Optional.ofNullable(this.pr.findProfessionalByEmailAndPassword(email, password));
		return login.orElseThrow(() -> new NoClientFound("No such Professional"));
	}
	
	public Professional register(Professional body) {
		return Optional.ofNullable(this.pr.save(body)).orElseThrow(() -> new NoClientFound("No such Professional"));
	}
	
	//to fix, when empty string? what to do? leave it or ... ?
	public Professional change(Professional body, String email, String password) throws ClientAlredyExist {
		
		//checking for existing client
		Optional<Professional> oldClient = Optional.ofNullable(this.pr.findProfessionalByEmailAndPassword(email, password));
		//checking for new client
		Optional<Professional> newClient = Optional.ofNullable(this.pr.findProfessionalByEmailAndPassword(body.getEmail(), body.getPassword()));
		
		if(oldClient.isEmpty()) {
			throw new ClientAlredyExist("I can not find client...");
		} else {
			Professional found = oldClient.get();
			//if new email is occupied than throw error
			if(newClient.isPresent() && !found.getEmail().equals(body.getEmail())){
				throw new ClientAlredyExist("Client already exist");
			} else {
				
				//Checks if company name has chanched
				if(!body.getFirm().equals(found.getFirm())) {
//					System.out.println("Name of Company has changed! from: " + found.getFirm() + " onto " + body.getFirm());
					List<Bid> bidList = this.br.findAllBidByProfFirm(found.getFirm());
//					System.out.println("I have retrive the Bid List " + bidList.size());
					
					//If company name has chacged -> rename cells in column "ProfFirm"
					for(Bid bid : bidList) {
//						System.out.println("I am changing the bid: " + bid.getProfFirm());
						bid.setProfFirm(body.getFirm());
//						System.out.print(" onto " + bid.getProfFirm());
						this.br.save(bid);
					}
				}
					
				found.setAddress(body.getAddress());
				found.setEmail(body.getEmail());
				found.setFirm(body.getFirm());
				found.setFullName(body.getFullName());
				found.setPassword(body.getPassword());
				found.setPhone(body.getPhone());
				
				return Optional.ofNullable(this.pr.save(found)).orElseThrow(() -> new ClientAlredyExist("Can not save Client"));
			} 
		}
	}
	
	public Boolean delete(String email, String password) {
		Professional prof = Optional.of(this.pr.findProfessionalByEmailAndPassword(email, password))
				.orElseThrow(() -> new NoClientFound("Can not find this professional"));
		if(Optional.ofNullable(prof).isPresent()) {
			this.pr.delete(prof);
			Optional<Professional> afterDelete = Optional.ofNullable(this.pr.findProfessionalByEmailAndPassword(email, password));
			return afterDelete.isEmpty();
		} else return false;
	}
	
	//needed to get professionals on map with address details
	public List<ProfPos> getProfessionals(double distance, double lat, double lng) {
//		System.out.println("Service started");
		
		return this.pr.findAll()
			.stream()
			.map(ProfPos::new)
			.map(pro -> { 
					pro.calcDis(lat, lng);
					return pro;
				})
			.filter(pro -> pro.getDistance() <= distance)
			.collect(Collectors.toList());
	}
	
}
