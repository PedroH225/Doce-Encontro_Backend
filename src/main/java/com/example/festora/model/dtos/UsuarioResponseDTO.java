package com.example.festora.model.dtos;

import com.example.festora.model.Usuario;

import lombok.Data;

@Data
public class UsuarioResponseDTO {

	private String id;

	private String nome;

	public UsuarioResponseDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
	}

}
