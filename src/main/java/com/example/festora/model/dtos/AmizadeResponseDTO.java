package com.example.festora.model.dtos;

import com.example.festora.model.Amizade;

import lombok.Data;

@Data
public class AmizadeResponseDTO {

	public String id;
	
	public UsuarioResponseDTO usuario;
	
	public UsuarioResponseDTO amigo;
	
	public String status;

	public AmizadeResponseDTO(Amizade amizade) {
		this.id = amizade.getId();
		this.usuario = new UsuarioResponseDTO(amizade.getUsuario());
		this.amigo = new UsuarioResponseDTO(amizade.getAmigo());
		this.status = amizade.getStatus().toString();
	}
	
}
