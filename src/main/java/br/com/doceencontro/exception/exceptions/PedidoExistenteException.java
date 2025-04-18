package br.com.doceencontro.exception.exceptions;

public class PedidoExistenteException extends ConflictException {
	private static final long serialVersionUID = 1L;
	
	public PedidoExistenteException() {
		super("O pedido de amizade já existe.");
	}

}
