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
		Optional<Consumer> idO = Optional.ofNullable(this.cr.findConsumerByEmailAndPassword(email, password));
		
		if(idO != null) {
			this.cr.delete(idO.get());
			return idO != null;
			
		} else return false;
	}
	
	public Consumer change(Consumer body, String email, String password) throws ClientAlredyExist {
		//check if client exist
		//Checking if consumer to change exists
		Consumer found = Optional.ofNullable(this.cr.findConsumerByEmailAndPassword(email, password))
				.orElseThrow(() -> new NoClientFound("No such Client"));
		System.out.println("found email: " + found.getEmail());
		
		//Chceking if consumers email is about to change?
		if(!body.getEmail().equals(found.getEmail())) {
			System.out.println("email are changing! new one: " + body.getEmail());
			Optional<Consumer> exisitngConsumer =  Optional.ofNullable(this.cr.findConsumerByEmail(body.getEmail()));
			System.out.println("Check if new email exist alredy?: " + exisitngConsumer.isPresent());
//			if consumers email Is about to change, than check if it is free to use
			if(exisitngConsumer.isPresent()) {
				//if email is occupaid than throw error
				System.out.println("new email is existing alredy...");
				throw new ClientAlredyExist("Client with this details already exist");
			}
		}
		System.out.println("set email from: " + found.getEmail() + " on to: " + body.getEmail());
		found.setPassword(body.getPassword());
		found.setEmail(body.getEmail());
		found.setFirm(body.getFirm());
		found.setFullName(body.getFullName());
		found.setPhone(body.getPhone());
		found.setAddress(body.getAddress());
		
		return Optional.ofNullable(this.cr.save(found)).orElseThrow(() -> new NoClientFound("Problem to save Client..."));
	}
}
