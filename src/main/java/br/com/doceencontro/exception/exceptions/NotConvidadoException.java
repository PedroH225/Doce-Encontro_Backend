package br.com.doceencontro.exception.exceptions;

public class NotConvidadoException extends ForbiddenException {
	private static final long serialVersionUID = 1L;

	public NotConvidadoException() {
		super("Você não está entre os convidados.");
	}

}
