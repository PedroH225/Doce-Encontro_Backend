package br.com.doceencontro.exception.exceptions;

public class NotAutorException extends ForbiddenException {
	private static final long serialVersionUID = 1L;

	public NotAutorException() {
		super("Você não é o autor do evento.");
	}

}
