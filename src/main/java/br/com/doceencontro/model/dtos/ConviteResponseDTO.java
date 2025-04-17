package br.com.doceencontro.model.dtos;

import java.util.List;
import java.util.stream.Collectors;

import br.com.doceencontro.model.Convite;
import lombok.Data;

@Data
public class ConviteResponseDTO {

	private String id;
	
	private String eventoId;
	
	private String organizador;
	
	private String titulo;

	private String descricao;
		
	public ConviteResponseDTO(Convite convite) {
		this.id = convite.getId();
		this.titulo = convite.getTitulo();
		this.descricao = convite.getDescricao();
		this.eventoId =  convite.getEvento().getId();
		this.organizador =  convite.getEvento().getOrganizador().getNome();

		
	}
	
}
