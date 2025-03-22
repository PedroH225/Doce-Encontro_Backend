package com.example.festora.model.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.example.festora.model.Evento;
import com.example.festora.model.Usuario;

import lombok.Data;

@Data
public class UsuarioDetailsDTO {

	private String id;

	private String nome;
	
	private String email;
	
	private List<EventoResponseDTO> eventosCriados;
	
	private List<EventoResponseDTO> eventosParticipados;
	
	public UsuarioDetailsDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		
		this.eventosCriados = converterEventoDTO(usuario.getEventosCriados());
		this.eventosParticipados = converterEventoDTO(usuario.getEventosParticipados());
		
	}

	private List<EventoResponseDTO> converterEventoDTO(List<Evento> eventos) {
		return eventos.stream()
				.map(e -> new EventoResponseDTO(e))
				.collect(Collectors.toList());
			
	}
}
