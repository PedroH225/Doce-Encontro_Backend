package br.com.doceencontro.utils;

import br.com.doceencontro.exception.exceptions.JaParticipandoException;
import br.com.doceencontro.exception.exceptions.NotAutorException;
import br.com.doceencontro.exception.exceptions.NotConvidadoException;
import br.com.doceencontro.exception.exceptions.NotParticipandoException;
import br.com.doceencontro.model.Evento;

public class EventoUtils {

	public static boolean isParticipando(Evento evento, String usuarioId) {
		return evento.getParticipantes().stream()
        .anyMatch(p -> p.getId().equals(usuarioId));
	}
	
	public static boolean isConvidado(Evento evento, String usuarioId) {
		return evento.getConvite().getDestinatarios().stream()
        .anyMatch(p -> p.getId().equals(usuarioId));
	}
	
	public static boolean verificarAutor(String usuarioId, Evento evento) {		
		return evento.getOrganizador().getId().equals(usuarioId);
	}
	
	public static void garantirParticipacao(Evento evento, String usuarioId) {
		if (!isParticipando(evento, usuarioId)) {
			throw new NotParticipandoException();
		}
	}
	
	public static void garantirNaoParticipacao(Evento evento, String usuarioId) {
		if (isParticipando(evento, usuarioId)) {
			throw new JaParticipandoException();
		}

	}
	
	public static void garantirConvidado(Evento evento, String usuarioId) {
		if (!isConvidado(evento, usuarioId)) {
			throw new NotConvidadoException();
		}

	}
	
	public static void garantirAutoria(Evento evento, String usuarioId) {
		if (!verificarAutor(usuarioId, evento)) {
			throw new NotAutorException();
		}

	}
	
}
