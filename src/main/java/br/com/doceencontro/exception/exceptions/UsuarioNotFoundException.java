package br.com.doceencontro.exception.exceptions;

public class UsuarioNotFoundException extends NotFoundException {
	private static final long serialVersionUID = 1L;

	public UsuarioNotFoundException() {
		super("O usuário não foi encontrado.");
	}
}
