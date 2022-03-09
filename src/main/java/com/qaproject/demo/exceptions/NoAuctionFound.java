package com.qaproject.demo.exceptions;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Can not find/create this Auction")
public class NoAuctionFound extends NoSuchElementException {
	
	private static final long serialVersionUID = -2178815030001906265L;

	public NoAuctionFound(String m) {
		super(m);
	}
	

}
