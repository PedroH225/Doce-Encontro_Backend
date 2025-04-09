package com.example.festora.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.festora.model.Evento;
import com.example.festora.model.Usuario;

@Service
public class EmailService {

	@Value("${festoraEmail}")
	private String festoraEmail;
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Async
	public void enviarEmailParticipantes(Evento evento, String assunto, String corpo) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom(festoraEmail);
		
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





