package com.example.festora.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RequisitoNotFoundException extends NotFoundException {
	private static final long serialVersionUID = 1L;

	public RequisitoNotFoundException() {
		super("O presente não foi encontrado.");
	}

}
