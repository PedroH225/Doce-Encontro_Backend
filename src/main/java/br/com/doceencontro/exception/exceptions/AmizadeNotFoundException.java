package br.com.doceencontro.exception.exceptions;

public class AmizadeNotFoundException extends NotFoundException {
	private static final long serialVersionUID = 1L;

	public AmizadeNotFoundException() {
		super("Amizade não encontrada.");
	}

}
