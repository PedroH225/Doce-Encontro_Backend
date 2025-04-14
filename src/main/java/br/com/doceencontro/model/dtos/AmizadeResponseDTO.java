package br.com.doceencontro.model.dtos;

import br.com.doceencontro.model.Amizade;
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
