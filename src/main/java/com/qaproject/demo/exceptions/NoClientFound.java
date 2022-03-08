package com.qaproject.demo.exceptions;

import javax.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.ResponseStatus;
		
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There are no such Client!")
public class NoClientFound extends EntityNotFoundException {
	
	private static final long serialVersionUID = 1L;

	public NoClientFound(String m) {
		super(m);
	}

}
