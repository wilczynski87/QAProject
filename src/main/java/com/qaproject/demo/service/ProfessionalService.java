package com.qaproject.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qaproject.demo.clients.Professional;
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
	
	public Boolean delete(Integer id) {
		Integer idO = Optional.of(id).orElseThrow(() -> new NoClientFound("Null for ID"));
		if(pr.existsById(idO)) {
			pr.deleteById(idO);
			return true;
		} else return false;
	}
	
	//to fix, when empty string? what to do? leave it or ... ?
	public Professional change(Professional body, String email, String password) {
		Professional found = this.pr.findProfessionalByEmailAndPassword(email, password);
		found.setAddress(body.getAddress());
		found.setEmail(body.getEmail());
		found.setFirm(body.getFirm());
		found.setFullName(body.getFullName());
		found.setPassword(body.getPassword());
		found.setPhone(body.getPhone());
		return Optional.ofNullable(this.pr.save(found)).orElseThrow(() -> new NoClientFound("No such Professional"));
	}
	
}
