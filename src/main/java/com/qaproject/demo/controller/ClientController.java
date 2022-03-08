package com.qaproject.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qaproject.demo.clients.Consumer;
import com.qaproject.demo.service.ConsumerService;

@RestController
public class ClientController {
	
	private ConsumerService service;
	
	@Autowired
	public ClientController(ConsumerService service) {
		this.service = service;
	}
	
	@GetMapping("/consumerLogin/{email}/{password}")
	@ResponseBody 
	public ResponseEntity<Consumer> logIn(@PathVariable("email") String email, @PathVariable("password") String password) {
		return new ResponseEntity<Consumer> (this.service.loginConsumer(email, password), HttpStatus.OK);
	}
}
