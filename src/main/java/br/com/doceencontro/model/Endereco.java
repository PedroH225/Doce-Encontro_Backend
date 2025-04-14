package br.com.doceencontro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "enderecos")
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	private String local;
	
	private String estado;
	
	private String cidade;
	
	private String rua;
	
	private Integer numero;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "evento_id", referencedColumnName = "id")
	@JsonIgnore
	private Evento evento;
	
}
