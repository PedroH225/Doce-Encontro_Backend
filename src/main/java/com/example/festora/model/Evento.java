package com.example.festora.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "eventos")
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	private String titulo;
	
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	private LocalDateTime data;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id", referencedColumnName = "id")
	private Endereco endereco;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	private Usuario organizador;
	
	@ManyToMany(mappedBy = "eventosParticipados")
	private List<Usuario> participantes;
	
	@OneToMany(mappedBy = "evento", fetch = FetchType.EAGER)
	private List<Arquivo> arquivos;
	
}
