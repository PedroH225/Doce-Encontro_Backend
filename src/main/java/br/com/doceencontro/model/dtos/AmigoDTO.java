package br.com.doceencontro.model.dtos;

import br.com.doceencontro.model.Amizade;
import br.com.doceencontro.utils.ConversorDTO;
import br.com.doceencontro.utils.IdToken;
import lombok.Data;

@Data
public class AmigoDTO {

	private String amizadeId;
	
	private UsuarioResponseDTO amigo;
	
	private UsuarioResponseDTO remetente;

	private String status;
	
	
	public AmigoDTO(Amizade amizade) {
		this.amizadeId = amizade.getId();
		
		String idLogado = IdToken.get();
		
		if (!amizade.getAmigo().getId().equals(idLogado)) {
			
			this.amigo = ConversorDTO.usuario(amizade.getAmigo());
			this.remetente = ConversorDTO.usuario(amizade.getUsuario());
			
		} else {
			this.amigo = ConversorDTO.usuario(amizade.getUsuario());
			this.remetente = ConversorDTO.usuario(amizade.getAmigo());		}
		
		this.status = amizade.getStatus().toString();
	}
}
