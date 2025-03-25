package com.example.festora.model.dtos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.festora.model.Mensagem;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class MensagemDTO {

	private String id;
	
	private String conteudo;
	
	private String dataEnvio;
	
	private UsuarioResponseDTO usuario;
	
	@JsonIgnore
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	
	public MensagemDTO(Mensagem mensagem) {
		this.id = mensagem.getId();
		this.usuario = new UsuarioResponseDTO(mensagem.getUsuario());
		this.conteudo = mensagem.getConteudo();
		this.dataEnvio = mensagem.getDataEnvio().format(dtf);
	}
}



