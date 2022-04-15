package com.qaproject.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qaproject.demo.clients.Consumer;
import com.qaproject.demo.clients.Professional;
import com.qaproject.demo.exceptions.NoClientFound;
import com.qaproject.demo.repositories.ConsumerRepo;
import com.qaproject.demo.repositories.ProfessionalRepo;

@Service
public class ClientService implements Clients<Object>{
	
	@Autowired
	private ConsumerRepo cr;
	
	@Autowired
	private ProfessionalRepo pr;

	@Override
	public Object getClient(String email, String password) {
		System.out.println("test");
		Optional<Professional> prof = Optional.ofNullable(this.pr.findProfessionalByEmailAndPassword(email, password));
		System.out.println(prof.isPresent());
		Optional<Consumer> cons = Optional.ofNullable(this.cr.findConsumerByEmailAndPassword(email, password));
		System.out.println("test after initialisation");
		
		if(prof.isPresent()) {
			System.out.println("professional exist!");
			return prof.get();
		} else if(cons.isPresent()) {
			System.out.println("Consumer exist!");
			return cons.get();
		} else throw new NoClientFound("No Professional or Consumer Found");
	}

}
