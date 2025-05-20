package br.com.doceencontro.aspects;

import java.util.List;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import br.com.doceencontro.model.Evento;
import br.com.doceencontro.model.Icone;
import br.com.doceencontro.model.Notificacao;
import br.com.doceencontro.model.Usuario;
import br.com.doceencontro.model.dtos.ConviteDTO;
import br.com.doceencontro.model.dtos.EventoResponseDTO;
import br.com.doceencontro.service.EmailService;
import br.com.doceencontro.service.EventoService;
import br.com.doceencontro.service.NotificacaoService;
import lombok.AllArgsConstructor;

@Aspect
@Component
@AllArgsConstructor
public class NotificacaoAspect {

	private NotificacaoService notificacaoService;
	
	private EmailService emailService;
	
	@AfterReturning(
			pointcut = "execution(* br.com.doceencontro.service.EventoService.editarEvento(..))",
			returning = "result")
	public void notificarEdicao(EventoResponseDTO result) {
		String eventoTitulo = result.getTitulo();
		
		List<Usuario> participantes = notificacaoService.findParticipantesComNotificacao(result);
		
		String notificacaoTitulo = "Alterações em " + eventoTitulo + ".";
		String notificacaoCorpo = "As informações do evento " + eventoTitulo + " foram alteradas.";
		
		Notificacao novaNotificacao =  new Notificacao(notificacaoTitulo, notificacaoCorpo, Icone.INFO);
		
		notificacaoService.notificarParticipantes(result, participantes, novaNotificacao);
		
		emailService.enviarEmailParticipantes(result, participantes, notificacaoTitulo, notificacaoCorpo);
		
	}	
}



