package com.qaproject.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qaproject.demo.clients.Consumer;
import com.qaproject.demo.exceptions.ClientAlredyExist;
import com.qaproject.demo.service.ConsumerService;

@CrossOrigin
@RestController
public class ClientController {
	
	private ConsumerService conSer;
	
	@Autowired
	public ClientController(ConsumerService conSer) {
		this.conSer = conSer;
	}
	
	@GetMapping("/consumerLogin/{email}/{password}")
	@ResponseBody 
	public ResponseEntity<Consumer> logInConsumer(@PathVariable("email") String email, @PathVariable("password") String password) {
		return new ResponseEntity<Consumer> (this.conSer.login(email, password), HttpStatus.OK);
	}
	
	@PostMapping("/consumerSignIn")
	public ResponseEntity<Consumer> registerConsumer(@RequestBody Consumer body) throws ClientAlredyExist {
		return new ResponseEntity<Consumer> (this.conSer.register(body), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/consumerDelete/{email}/{password}")
	public ResponseEntity<Boolean> deleteConsumer(@PathVariable("email") String email, @PathVariable("password") String password) {
		return new ResponseEntity<Boolean> (this.conSer.delete(email, password), HttpStatus.GONE);
	}
	@PutMapping("/consumerChange/{email}/{password}")
	public ResponseEntity<Consumer> changeConsumer(@RequestBody Consumer body, @PathVariable("email") String email, @PathVariable("password") String password) throws ClientAlredyExist {
		return new ResponseEntity<Consumer> (this.conSer.change(body, email, password), HttpStatus.ACCEPTED);
	}
}
