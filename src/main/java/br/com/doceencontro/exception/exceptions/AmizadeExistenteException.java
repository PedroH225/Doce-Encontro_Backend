package br.com.doceencontro.exception.exceptions;

public class AmizadeExistenteException extends ConflictException {
	private static final long serialVersionUID = 1L;

	public AmizadeExistenteException() {
		super("Usuário já adicionado.");
	}

}
