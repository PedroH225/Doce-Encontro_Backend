package br.com.doceencontro.model.dtos;

import java.util.List;
import java.util.stream.Collectors;

import br.com.doceencontro.model.Convite;
import lombok.Data;

@Data
public class ConviteDTO {

	private String id;
	
	private String titulo;

	private String descricao;
	
	private List<UsuarioResponseDTO> destinatario;
	
	public ConviteDTO(Convite convite) {
		this.id = convite.getId();
		this.titulo = convite.getTitulo();
		this.descricao = convite.getDescricao();
		this.destinatario = convite.getDestinatarios().stream()
				.map(u -> new UsuarioResponseDTO(u))
				.collect(Collectors.toList());
	}
	
}
