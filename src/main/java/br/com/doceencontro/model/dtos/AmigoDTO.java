package br.com.doceencontro.model.dtos;

import br.com.doceencontro.model.Amizade;
import br.com.doceencontro.model.StatusAmizade;
import br.com.doceencontro.model.Usuario;

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
