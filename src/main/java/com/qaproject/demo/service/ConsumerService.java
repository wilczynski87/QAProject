package com.qaproject.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qaproject.demo.clients.Consumer;
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
	
	public Consumer register(Consumer body) {
		return Optional.of(this.cr.save(body)).orElseThrow(() -> new NoClientFound("No such Client"));
	}
	
	public Boolean delete(Integer id) {
		Integer idO = Optional.of(id).orElseThrow(() -> new NoClientFound("Null for ID"));
		if(cr.existsById(idO)) {
			cr.deleteById(idO);
			return true;
		} else return false;
	}
	
	public Consumer change(Consumer body, String email, String password) {
		Consumer found = this.login(email, password);
		found.setAddress(body.getAddress());
		found.setEmail(body.getEmail());
		found.setFirm(body.getFirm());
		found.setFullName(body.getFullName());
		found.setPassword(body.getPassword());
		found.setPhone(body.getPhone());
		return Optional.of(this.cr.save(body)).orElseThrow(() -> new NoClientFound("No such Client"));
	}
}
