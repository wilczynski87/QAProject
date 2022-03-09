package com.qaproject.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qaproject.demo.clients.Professional;
import com.qaproject.demo.exceptions.NoClientFound;
import com.qaproject.demo.repositories.ProfessionalRepo;

@Service
public class ProfessionalService {
	
	private ProfessionalRepo pf;
	
	@Autowired
	public ProfessionalService(ProfessionalRepo pf) {
		this.pf = pf;
	}

	public Professional login(String email, String password) {
		Optional<Professional> login = Optional.ofNullable(this.pf.findProfessionalByEmailAndPassword(email, password));
		return login.orElseThrow(() -> new NoClientFound("No such Client"));
	}
	
	public Professional register(Professional body) {
		return Optional.ofNullable(this.pf.save(body)).orElseThrow(() -> new NoClientFound("No such Client"));
	}
	
	public Boolean delete(Integer id) {
		Integer idO = Optional.of(id).orElseThrow(() -> new NoClientFound("Null for ID"));
		if(pf.existsById(idO)) {
			pf.deleteById(idO);
			return true;
		} else return false;
	}
	
	//to fix, when empty string? what to do? leave it or ... ?
	public Professional change(Professional body, String email, String password) {
		Professional found = this.login(email, password);
		found.setAddress(body.getAddress());
		found.setEmail(body.getEmail());
		found.setFirm(body.getFirm());
		found.setFullName(body.getFullName());
		found.setPassword(body.getPassword());
		found.setPhone(body.getPhone());
		return pf.save(found);
	}
	
}
