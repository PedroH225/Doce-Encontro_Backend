package br.com.doceencontro.model.dtos;

import br.com.doceencontro.model.Amizade;
import br.com.doceencontro.utils.IdToken;
import lombok.Data;

@Data
public class AmigoDTO {

	private String amizadeId;
	
	private UsuarioResponseDTO amigo;
	
	private String status;
	
	
	public AmigoDTO(Amizade amizade) {
		this.amizadeId = amizade.getId();
		
		String idLogado = IdToken.get();
		
		if (!amizade.getAmigo().getId().equals(idLogado)) {
			
			this.amigo = new UsuarioResponseDTO(amizade.getAmigo());
			
		} else {
			this.amigo = new UsuarioResponseDTO(amizade.getUsuario());
		}
		
		this.status = amizade.getStatus().toString();
	}
}
