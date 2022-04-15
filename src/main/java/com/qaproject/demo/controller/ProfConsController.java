package com.qaproject.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qaproject.demo.service.ClientService;

@CrossOrigin
@Controller
public class ProfConsController {
	
	@Autowired
	private ClientService clientService;

	@GetMapping("/getUser/{email}/{password}")
	@ResponseBody
	public ResponseEntity<Object> getClient(@PathVariable("email") String email, @PathVariable("password") String password) {
		return new ResponseEntity<Object> (this.clientService.getClient(email, password), HttpStatus.OK);
	}
	
	

}
