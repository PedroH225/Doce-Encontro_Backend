package br.com.doceencontro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.doceencontro.model.Evento;
import br.com.doceencontro.model.Usuario;

@Service
public class EmailService {

	@Value("${dcenEmail}")
	private String dcenEmail;
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Async
	public void enviarEmailParticipantes(Evento evento, String assunto, String corpo) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom(dcenEmail);
		
		message.setSubject(assunto);
		
		for (Usuario participante : evento.getParticipantes()) {
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





