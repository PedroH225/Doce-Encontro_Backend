package com.example.festora.service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.festora.model.Evento;
import com.example.festora.model.Notificacao;
import com.example.festora.repository.NotificacaoRepository;

@Service
public class NotificacaoService {

	private EventoService eventoService;
	
	private NotificacaoRepository notificacaoRepository;

	public NotificacaoService(EventoService eventoService, NotificacaoRepository notificacaoRepository) {
		this.eventoService = eventoService;
		this.notificacaoRepository = notificacaoRepository;
	}
	
	public void notificarParticipantes(String eventoId, String titulo, String corpo) {
		Evento evento = eventoService.findById(eventoId);
		Notificacao notificacao = new Notificacao(null, titulo, corpo, LocalDateTime.now(), new ArrayList<>());
		notificacao.enviarNotificacao(evento.getParticipantes());
		
		notificacaoRepository.save(notificacao);
	}
}



