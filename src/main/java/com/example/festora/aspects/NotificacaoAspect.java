package com.example.festora.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.example.festora.model.dtos.EventoResponseDTO;
import com.example.festora.service.NotificacaoService;

@Aspect
@Component
public class NotificacaoAspect {

	private NotificacaoService notificacaoService;

	public NotificacaoAspect(NotificacaoService notificacaoService) {
		this.notificacaoService = notificacaoService;
	}
	
	@AfterReturning(
			pointcut = "execution(* com.example.festora.service.EventoService.editarEvento(..))",
			returning = "result")
	public void notificarEdicao(EventoResponseDTO result) {
		String eventoTitulo = result.getTitulo();
		String eventoId = result.getId();
		
		String notificacaoTitulo = "Alterações em " + eventoTitulo + ".";
		String notificacaoCorpo = "As informações do evento " + eventoTitulo + " foram alteradas.";
		
		notificacaoService.notificarParticipantes(eventoId, notificacaoTitulo, notificacaoCorpo);
	}
	
	
}



