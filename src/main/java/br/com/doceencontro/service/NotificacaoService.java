package br.com.doceencontro.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.doceencontro.model.Evento;
import br.com.doceencontro.model.Notificacao;
import br.com.doceencontro.repository.NotificacaoRepository;

@Service
public class NotificacaoService {

	private EventoService eventoService;
	
	private NotificacaoRepository notificacaoRepository;

	public NotificacaoService(EventoService eventoService, NotificacaoRepository notificacaoRepository) {
		this.eventoService = eventoService;
		this.notificacaoRepository = notificacaoRepository;
	}
	
	public void notificarParticipantes(Evento evento, String titulo, String corpo) {
		Notificacao notificacao = new Notificacao(null, titulo, corpo, LocalDateTime.now(), new ArrayList<>());
		notificacao.enviarNotificacao(evento.getParticipantes());
		
		notificacaoRepository.save(notificacao);
	}
	
	public List<Notificacao> obterNotificacoesUsuario(String usuarioId) {
		return notificacaoRepository.findAllByUsuariosId(usuarioId);
	}
}



