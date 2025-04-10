package com.example.festora.exception.exceptions;

public class JaParticipandoException extends ConflictException {
	private static final long serialVersionUID = 1L;

	public JaParticipandoException() {
		super("Você já está participando do evento.");
	}

}
