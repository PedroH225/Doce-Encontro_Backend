package com.example.festora.model.dtos;

import com.example.festora.model.Amizade;
import com.example.festora.model.StatusAmizade;
import com.example.festora.model.Usuario;

public class AmigoDTO {

	public String amizadeId;
	
	public UsuarioResponseDTO amigo;
	
	public String status;
	
	
	public AmigoDTO(Amizade amizade, String usuarioId) {
		this.amizadeId = amizade.getId();
		
		if (!amizade.getAmigo().getId().equals(usuarioId)) {
			
			this.amigo = new UsuarioResponseDTO(amizade.getAmigo());
			
		} else {
			this.amigo = new UsuarioResponseDTO(amizade.getUsuario());
		}
		
		this.status = amizade.getStatus().toString();
	}
}
