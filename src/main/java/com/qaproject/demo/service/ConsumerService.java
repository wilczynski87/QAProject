package com.qaproject.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qaproject.demo.clients.Consumer;
import com.qaproject.demo.exceptions.ClientAlredyExist;
import com.qaproject.demo.exceptions.NoClientFound;
import com.qaproject.demo.repositories.ConsumerRepo;

@Service
public class ConsumerService {
	
	private ConsumerRepo cr;
	
	@Autowired
	public ConsumerService(ConsumerRepo cr) {
		this.cr = cr;
	}

	public Consumer login(String email, String password) {
		Optional<Consumer> login = Optional.ofNullable(this.cr.findConsumerByEmailAndPassword(email, password));
		return login.orElseThrow(() -> new NoClientFound("No such Client"));
	}
	
	public Consumer register(Consumer body) throws ClientAlredyExist {
		String email = Optional.of(body.getEmail()).orElseThrow(() -> new NoClientFound("Can not find email"));
		String password = Optional.of(body.getPassword()).orElseThrow(() -> new NoClientFound("Can not find password"));
		
		if(Optional.ofNullable(this.cr.findConsumerByEmailAndPassword(email, password)).isPresent()) {
			throw new ClientAlredyExist("Client already exists");
		} else return Optional.of(this.cr.save(body)).orElseThrow(() -> new NoClientFound("Can not save Consumer"));
	}
	
	public Boolean delete(String email, String password) {
//		Consumer idO = null;
//		idO = Optional.ofNullable(this.cr.findConsumerByEmailAndPassword(email, password)).orElseThrow();
		Optional<Consumer> idO = Optional.ofNullable(this.cr.findConsumerByEmailAndPassword(email, password));
		
		if(idO.isPresent()) {
			this.cr.delete(idO.orElseThrow());
			//this.cr.deleteConsumerByEmailAndPassword(email, password);
			
//			if(idO.isEmpty()) {
			return idO.isEmpty();
			
		} else return false;
	}
	
	public Consumer change(Consumer body, String email, String password) throws ClientAlredyExist {
		//check if client exist
		//Checking if consumer to change exists
		Consumer found = Optional.of(this.cr.findConsumerByEmailAndPassword(email, password))
				.orElseThrow(() -> new NoClientFound("No such Client"));
		
		//Chceking if consumers email is about to change?
		if(!body.getEmail().equals(found.getEmail())) {
			Optional<Consumer> exisitngConsumer =  Optional.ofNullable(this.cr.findConsumerByEmail(body.getEmail()));
			
//			if consumers email Is about to change, than check if it is free to use
			if(exisitngConsumer.isPresent()) {
				//if email is occupaid than throw error
				throw new ClientAlredyExist("Client with this details already exist");
			}
		}
		found.setAddress(body.getAddress());
		found.setEmail(body.getEmail());
		found.setFirm(body.getFirm());
		found.setFullName(body.getFullName());
		found.setPassword(body.getPassword());
		found.setPhone(body.getPhone());
		
		return Optional.of(this.cr.save(found)).orElseThrow(() -> new NoClientFound("Problem to dave Client..."));
	}
}
