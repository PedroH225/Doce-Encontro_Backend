package br.com.doceencontro.aspects;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.doceencontro.model.Amizade;
import br.com.doceencontro.model.Icone;
import br.com.doceencontro.model.Notificacao;
import br.com.doceencontro.model.TipoFinalizacaoEmail;
import br.com.doceencontro.model.Usuario;
import br.com.doceencontro.model.dtos.AmigoDTO;
import br.com.doceencontro.model.dtos.ConviteDTO;
import br.com.doceencontro.model.dtos.EventoResponseDTO;
import br.com.doceencontro.model.dtos.UsuarioResponseDTO;
import br.com.doceencontro.service.AmizadeService;
import br.com.doceencontro.service.EmailService;
import br.com.doceencontro.service.NotificacaoService;
import br.com.doceencontro.utils.ConversorDTO;
import lombok.AllArgsConstructor;

@Aspect
@Component
@AllArgsConstructor
public class NotificacaoAspect {

	private NotificacaoService notificacaoService;

	private AmizadeService amizadeService;

	private EmailService emailService;

	@Async
	@AfterReturning(pointcut = "execution(* br.com.doceencontro.service.EventoService.editarEvento(..))", returning = "result")
	public void notificarEdicao(EventoResponseDTO result) {
		String eventoTitulo = result.getTitulo();

		List<Usuario> participantes = notificacaoService.findParticipantesComNotificacao(result);

		String notificacaoTitulo = "Alterações em " + eventoTitulo + ".";
		String notificacaoCorpo = "As informações do evento " + eventoTitulo + " foram alteradas.";

		Notificacao novaNotificacao = new Notificacao(notificacaoTitulo, notificacaoCorpo, Icone.INFO);

		notificacaoService.notificarParticipantes(result, participantes, novaNotificacao);

		emailService.enviarEmailParticipantes(result, participantes, notificacaoTitulo, notificacaoCorpo);

	}

	@Async
	@AfterReturning(pointcut = "execution(* br.com.doceencontro.service.ConviteService.convidar(..))", returning = "result")
	public void notificarConvidados(ConviteDTO result) {
		if (result.getDestinatarios() == null || result.getDestinatarios().isEmpty()) {
			return;
		}

		Notificacao novaNotificacao = new Notificacao(result.getTitulo(), result.getDescricao(), Icone.MAIL);

		notificacaoService.notificarUsuarios(result.getDestinatarios(), novaNotificacao);

		emailService.enviarEmail(result.getDestinatarios(), novaNotificacao, TipoFinalizacaoEmail.CONVITE);
	}

	@Async
	@AfterReturning(pointcut = "execution(* br.com.doceencontro.service.AmizadeService.adicionarAmigo(..))", returning = "result")
	public void notificarPedido(AmigoDTO result) {
		Amizade amizade = amizadeService.findById(result.getAmizadeId());

		Notificacao novaNotificacao = new Notificacao("Novo pedido de amizade",
				String.format("%s acaba de te enviar um pedido de amizade!", amizade.getUsuario().getNome()),
				Icone.PERSONADD);

		notificacaoService.notificarUsuario(amizade.getAmigo(), novaNotificacao);

		emailService.enviarEmail(ConversorDTO.usuario(amizade.getAmigo()), novaNotificacao, TipoFinalizacaoEmail.PADRAO);
	}

	@Async
	@AfterReturning(pointcut = "execution(* br.com.doceencontro.service.AmizadeService.aceitarPedido(..))", returning = "result")
	public void notificarPedidoAceito(AmigoDTO result) {
		Amizade amizade = amizadeService.findById(result.getAmizadeId());

		Notificacao novaNotificacao = new Notificacao("Pedido de amizade aceito.",
				String.format("%s acaba de aceitar seu pedido de amizade.", amizade.getAmigo().getNome()),
				Icone.SUCCESS);
		

		notificacaoService.notificarUsuario(amizade.getUsuario(), novaNotificacao);

		emailService.enviarEmail(ConversorDTO.usuario(amizade.getUsuario()), novaNotificacao,
				TipoFinalizacaoEmail.PADRAO);
	}

}
