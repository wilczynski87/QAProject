package com.qaproject.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qaproject.demo.clients.Client;
import com.qaproject.demo.clients.Professional;
import com.qaproject.demo.exceptions.ClientAlredyExist;
import com.qaproject.demo.exceptions.NoClientFound;
import com.qaproject.demo.repositories.ProfessionalRepo;

@Service
public class ProfessionalService {
	
	private ProfessionalRepo pr;
	
	@Autowired
	public ProfessionalService(ProfessionalRepo pr) {
		this.pr = pr;
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
		
		if(!oldClient.isPresent()) {
			throw new ClientAlredyExist("I can not find client...");
		} else {
			Professional found = oldClient.get();
			if(newClient.isPresent() && !found.getEmail().equals(body.getEmail())){
				throw new ClientAlredyExist("Client already exist");
			} else {
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
			return Optional.ofNullable(prof).isEmpty();
		} else return false;
	}
	
}
