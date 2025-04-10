package com.example.festora.exception.exceptions;

public class NotParticipandoException extends ForbiddenException {
	private static final long serialVersionUID = 1L;

	public NotParticipandoException() {
		super("Você não está participando do evento.");
	}

	
}
