package com.example.festora.exception.exceptions;

public class ChatNotFoundException extends NotFoundException {
	private static final long serialVersionUID = 1L;

	public ChatNotFoundException() {
		super("Chat não foi encontrado.");
	}

}
