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
import br.com.doceencontro.model.Usuario;
import br.com.doceencontro.model.dtos.EventoResponseDTO;
import br.com.doceencontro.model.dtos.UsuarioResponseDTO;

@Service
public class EmailService {

	@Value("${dcenEmail}")
	private String dcenEmail;

	@Autowired
	private JavaMailSender javaMailSender;

	@Async
	public void enviarEmailParticipantes(EventoResponseDTO evento, List<Usuario> participantes, String assunto,
			String corpo) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom(dcenEmail);

		message.setSubject(assunto);

		for (Usuario participante : participantes) {
			if (!participante.getNome().equalsIgnoreCase(evento.getOrganizador())) {

				String saudacao = "Olá " + participante.getNome() + ", ";
				message.setText(saudacao + Character.toLowerCase(corpo.charAt(0)) + corpo.substring(1));

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
	public void enviarEmail(List<UsuarioResponseDTO> destinatarios, Notificacao novaNotificacao) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom(dcenEmail);

		message.setSubject(novaNotificacao.getTitulo());

		for (UsuarioResponseDTO destinatario : destinatarios) {

			String saudacao = "Olá " + destinatario.getNome() + ", ";
			message.setText(saudacao + novaNotificacao.getCorpo());

			message.setTo(destinatario.getEmail());
			try {
				javaMailSender.send(message);
			} catch (MailException e) {
				System.out.println("Erro ao enviar email: " + e.getMessage());
			}

		}
	}
}
