package br.com.doceencontro.model.dtos;

import br.com.doceencontro.model.Amizade;
import lombok.Data;

@Data
public class AmigoDTO {

	private String amizadeId;
	
	private UsuarioResponseDTO amigo;
	
	private String status;
	
	
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
