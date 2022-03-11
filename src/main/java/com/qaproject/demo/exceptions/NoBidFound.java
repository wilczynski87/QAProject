package com.qaproject.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Could not found/creatr Bid")
public class NoBidFound extends NullPointerException {

	private static final long serialVersionUID = -3239399483716220638L;

	public NoBidFound(String m) {
		super(m);
	}
}
