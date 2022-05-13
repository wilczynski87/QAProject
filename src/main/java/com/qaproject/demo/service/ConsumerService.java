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
	
//	public Boolean delete(Integer id) {
//		Integer idO = Optional.of(id).orElseThrow(() -> new NoClientFound("Null for ID"));
//		if(cr.existsById(idO)) {
//			cr.deleteById(idO);
//			return true;
//		} else return false;
//	}
	
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
		Consumer found = Optional.of(this.cr.findConsumerByEmailAndPassword(email, password))
				.orElseThrow(() -> new NoClientFound("No such Client")) ;
		found.setAddress(body.getAddress());
		found.setEmail(body.getEmail());
		found.setFirm(body.getFirm());
		found.setFullName(body.getFullName());
		found.setPassword(body.getPassword());
		found.setPhone(body.getPhone());
		//check if new client already exist
		if(Optional.ofNullable(this.cr.findConsumerByEmailAndPassword(found.getEmail(), found.getPassword())).isPresent()) {
			throw new ClientAlredyExist("Client with this details already exist");
		} else return Optional.of(this.cr.save(found)).orElseThrow(() -> new NoClientFound("No such Client"));
	}
}
