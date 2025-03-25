package com.example.festora.model;

import java.security.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mensagens")
public class Mensagem {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	private String conteudo;
	
	@Column(name = "data_envio")
	private LocalDateTime dataEnvio;
	
	@ManyToOne
	@JoinColumn(name = "chat_id", referencedColumnName = "id")
	private Chat chat;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	private Usuario usuario;
	
	public Mensagem(Chat chat, Usuario usuario, String conteudo) {
		this.id = null;
		this.dataEnvio = LocalDateTime.now();
		this.conteudo = conteudo;
		this.chat = chat;
		this.usuario = usuario;
		
	}
}





