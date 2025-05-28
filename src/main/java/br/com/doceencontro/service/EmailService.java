package br.com.doceencontro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.doceencontro.model.Notificacao;
import br.com.doceencontro.model.TipoFinalizacaoEmail;
import br.com.doceencontro.model.Usuario;
import br.com.doceencontro.model.dtos.EventoResponseDTO;
import br.com.doceencontro.model.dtos.UsuarioResponseDTO;

@Service
public class EmailService {

	@Value("${dcenEmail}")
	private String dcenEmail;

	@Autowired
	private JavaMailSender javaMailSender;

	private String criarSaudacao(String nomeUsuario) {
		return "Prezado(a) " + nomeUsuario + ",\n\n";
	}

	private String criarFinalizacao(TipoFinalizacaoEmail tipo) {
		StringBuilder finalizacao = new StringBuilder();

		switch (tipo) {
		case CONVITE:
			finalizacao.append("\nContamos com sua presença!");
			break;
		case AGRADECIMENTO:
			finalizacao.append("\nAgradecemos pela atenção.");
			break;
		case PADRAO:
		default:
			break;
		}

		finalizacao.append("\n\nAtenciosamente,\n");
		finalizacao.append("Plataforma Doce Encontro\n");
		finalizacao.append("═════════ ❖ ═════════\n");
		finalizacao.append("Esta é uma mensagem automática. Por favor, não responda este e-mail.");

		return finalizacao.toString();
	}

	@Async
	public void enviarEmailParticipantes(EventoResponseDTO evento, List<Usuario> participantes, String assunto,
			String corpo) {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(dcenEmail);

		message.setSubject(assunto);

		String finalizacao = criarFinalizacao(TipoFinalizacaoEmail.PADRAO);

		for (Usuario participante : participantes) {
			if (!participante.getNome().equalsIgnoreCase(evento.getOrganizador())) {

				message.setText(criarSaudacao(participante.getNome()) + corpo + finalizacao);

				message.setTo(participante.getEmail());
				try {
					javaMailSender.send(message);
				} catch (MailException e) {
					System.out.println("Erro ao enviar email: " + e.getMessage());
				}
			}
		}
	}

	@Async
	public void enviarEmails(List<UsuarioResponseDTO> destinatarios, Notificacao novaNotificacao,
			TipoFinalizacaoEmail tipo) {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(dcenEmail);

		message.setSubject(novaNotificacao.getTitulo());

		String finalizacao = criarFinalizacao(tipo);

		for (UsuarioResponseDTO destinatario : destinatarios) {

			message.setText(criarSaudacao(destinatario.getNome()) + novaNotificacao.getCorpo() + finalizacao);

			message.setTo(destinatario.getEmail());

			try {
				javaMailSender.send(message);
			} catch (MailException e) {
				System.out.println("Erro ao enviar email: " + e.getMessage());
			}

		}
	}

	public void enviarEmail(UsuarioResponseDTO destinatario, Notificacao novaNotificacao, TipoFinalizacaoEmail tipo) {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(dcenEmail);

		message.setSubject(novaNotificacao.getTitulo());

		String finalizacao = criarFinalizacao(tipo);

		message.setText(criarSaudacao(destinatario.getNome()) + novaNotificacao.getCorpo() + finalizacao);

		message.setTo(destinatario.getEmail());

		try {
			javaMailSender.send(message);
		} catch (MailException e) {
			System.out.println("Erro ao enviar email: " + e.getMessage());
		}

	}
}
