package com.example.festora.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "amizades")
public class Amizade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "usuarios_id")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "amigo_id")
	private Usuario amigo;
	
	@Enumerated(EnumType.STRING)
	private StatusAmizade status;
}
