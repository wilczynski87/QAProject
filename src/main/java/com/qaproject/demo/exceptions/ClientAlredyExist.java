package com.qaproject.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.IM_USED, reason = "Client already exists!")
public class ClientAlredyExist extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2209059217373249075L;

	public ClientAlredyExist(String m) {
		super(m);
	}
}
