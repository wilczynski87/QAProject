package com.qaproject.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qaproject.demo.address.ProfPos;
import com.qaproject.demo.clients.Professional;
import com.qaproject.demo.exceptions.ClientAlredyExist;
import com.qaproject.demo.service.ProfessionalService;

@CrossOrigin
@Controller
public class ProfessionalController {
	
	private ProfessionalService ps;
	
	@Autowired
	public ProfessionalController(ProfessionalService ps) {
		this.ps = ps;
	}
	
	@GetMapping("/professionalLogin/{email}/{password}")
	@ResponseBody 
	public ResponseEntity<Professional> logInProfessional(@PathVariable("email") String email, @PathVariable("password") String password) {
		return new ResponseEntity<Professional> (this.ps.login(email, password), HttpStatus.OK);
	}
	
	@PostMapping("/professionalSignIn")
	public ResponseEntity<Professional> registerProfessional(@RequestBody Professional body) {
		return new ResponseEntity<Professional> (this.ps.register(body), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/professionalDelete/{email}/{password}")
	public ResponseEntity<Boolean> deleteProfessional(@PathVariable("email") String email, @PathVariable("password") String password) {
		return new ResponseEntity<Boolean> (this.ps.delete(email, password), HttpStatus.GONE);
	}
	
	@PutMapping("/professionalChange/{email}/{password}")
	public ResponseEntity<Professional> changeConsumer(@RequestBody Professional body, @PathVariable("email") String email, @PathVariable("password") String password) throws ClientAlredyExist {
		return new ResponseEntity<Professional> (this.ps.change(body, email, password), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/profPos/{distance}/{lat}/{lng}")
	public ResponseEntity<List<ProfPos>> getProfessionals(@PathVariable("distance") double distance, @PathVariable("lat") double lat, @PathVariable("lng") double lng) {
//		System.out.println("Recived a query");
		return new ResponseEntity<List<ProfPos>> (this.ps.getProfessionals(distance, lat, lng), HttpStatus.OK);
	}
	
}
