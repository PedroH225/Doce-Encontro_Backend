package com.example.festora.service;

import java.time.LocalDateTime;

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
	
	public void notificarParticipantes(Evento evento, String titulo, String corpo) {
		Notificacao notificacao = new Notificacao(null, titulo, corpo, LocalDateTime.now(), evento.getParticipantes());
		
		notificacaoRepository.save(notificacao);
	}
}



