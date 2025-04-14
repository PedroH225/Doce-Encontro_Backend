package br.com.doceencontro.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "convites")
public class Convite {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	private String titulo;
	
	private String descricao;
	
	@ManyToMany()
	@JoinTable(name = "convite_usuario",
	joinColumns = @JoinColumn(name = "convite_id"), inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private List<Usuario> destinatarios;

	@OneToOne()
	@JoinColumn(name = "evento_id", referencedColumnName = "id")
	private Evento evento;

	public Convite(Evento evento, List<Usuario> destinatarios) {
		this.evento = evento;
		this.destinatarios = destinatarios;
		this.titulo = String.format("Convite para o %s: %s.", evento.getTipo().toString(), evento.getTitulo());

		this.descricao = String.format("%s acaba de te convidar para o %s: %s. Aceite o convite para participar!.",
				evento.getOrganizador().getNome(), evento.getTipo().toString(), evento.getTitulo());
	}

}
