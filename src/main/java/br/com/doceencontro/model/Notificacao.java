package br.com.doceencontro.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	
	@Enumerated(EnumType.STRING)
	private Icone icone;
	
	@JsonIgnore
	@ManyToMany()
	@JoinTable(name = "notificacao_usuario", 
		joinColumns = @JoinColumn(name = "notificacao_id"),
		inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	
	
	public void enviarNotificacao(List<Usuario> usuarios) {
		for (Usuario usuario : usuarios) {
			this.usuarios.add(usuario);
			usuario.getNotificacoes().add(this);
		}
	}

	public Notificacao(String titulo, String corpo, Icone icone) {
		this.titulo = titulo;
		this.corpo = corpo;
		this.data = LocalDateTime.now();
		this.icone = icone;
	}
}



