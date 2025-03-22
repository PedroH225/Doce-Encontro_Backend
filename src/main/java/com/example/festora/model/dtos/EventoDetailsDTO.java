package com.example.festora.model.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.festora.model.Endereco;
import com.example.festora.model.Evento;
import com.example.festora.model.Requisito;
import com.example.festora.model.Tipo;
import com.example.festora.model.Usuario;

import lombok.Data;

@Data
public class EventoDetailsDTO {

	private String id;

	private String titulo;

	private String descricao;

	private Tipo tipo;

	private Endereco endereco;

	private UsuarioResponseDTO organizador;

	private List<RequisitoResponseDTO> requisitos;
	
	private List<UsuarioResponseDTO> participantes = new ArrayList<UsuarioResponseDTO>();

	public EventoDetailsDTO(Evento evento) {
		this.id = evento.getId();
		this.titulo = evento.getTitulo();
		this.descricao = evento.getDescricao();
		this.tipo = evento.getTipo();
		this.endereco = evento.getEndereco();
		this.requisitos = evento.getRequisitos().stream()
				.map(r -> new RequisitoResponseDTO(r))
				.collect(Collectors.toList());
				
		this.organizador = new UsuarioResponseDTO(evento.getOrganizador());
		this.participantes = evento.getParticipantes().stream().map(u -> new UsuarioResponseDTO(u))
				.collect(Collectors.toList());
		
	}
}
