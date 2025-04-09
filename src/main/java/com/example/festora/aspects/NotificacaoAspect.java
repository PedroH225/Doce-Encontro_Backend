package com.example.festora.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.example.festora.model.Evento;
import com.example.festora.model.Usuario;
import com.example.festora.model.dtos.EventoResponseDTO;
import com.example.festora.service.EmailService;
import com.example.festora.service.EventoService;
import com.example.festora.service.NotificacaoService;

@Aspect
@Component
public class NotificacaoAspect {

	private NotificacaoService notificacaoService;
	
	private EmailService emailService;
	
	private EventoService eventoService;
	
	public NotificacaoAspect(NotificacaoService notificacaoService, EmailService emailService,
			EventoService eventoService) {
		this.notificacaoService = notificacaoService;
		this.emailService = emailService;
		this.eventoService = eventoService;
	}
	
	@AfterReturning(
			pointcut = "execution(* com.example.festora.service.EventoService.editarEvento(..))",
			returning = "result")
	public void notificarEdicao(EventoResponseDTO result) {
		String eventoTitulo = result.getTitulo();
		String eventoId = result.getId();
		
		Evento buscarEvento = eventoService.findById(eventoId);
		
		String notificacaoTitulo = "Alterações em " + eventoTitulo + ".";
		String notificacaoCorpo = "As informações do evento " + eventoTitulo + " foram alteradas.";
		
		notificacaoService.notificarParticipantes(buscarEvento, notificacaoTitulo, notificacaoCorpo);
		
		emailService.enviarEmailParticipantes(buscarEvento, notificacaoTitulo, notificacaoCorpo);
		
	}
	
	
}



