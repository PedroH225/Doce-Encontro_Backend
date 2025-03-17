package com.example.festora.model.dtos;

import com.example.festora.model.Endereco;
import com.example.festora.model.Evento;
import com.example.festora.model.Tipo;
import com.example.festora.model.Usuario;

import lombok.Data;

@Data
public class EventoResponseDTO {

	private String id;
	
	private String titulo;
	
	private String  descricao;
	
	private Tipo tipo;
	
	private Endereco endereco;
	
	private UsuarioResponseDTO organizador;
	
	public EventoResponseDTO(Evento evento) {
		this.id = evento.getId();
		this.titulo = evento.getTitulo();
		this.descricao = evento.getDescricao();
		this.tipo = evento.getTipo();
		this.endereco = evento.getEndereco();
		this.organizador = new UsuarioResponseDTO(evento.getOrganizador());
	}
}



