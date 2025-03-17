package com.example.festora.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notificacoes")
public class Notificacao {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	private String titulo;
	
	private String corpo;
	
	private LocalDateTime data;
	
	@ManyToMany()
	@JoinTable(name = "notificacao_usuario", 
		joinColumns = @JoinColumn(name = "notificacao_id"),
		inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private List<Usuario> usuarios;

}
