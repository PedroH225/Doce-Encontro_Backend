package br.com.doceencontro.model.dtos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.doceencontro.model.Endereco;
import br.com.doceencontro.model.Evento;
import br.com.doceencontro.model.Tipo;
import br.com.doceencontro.model.Usuario;
import lombok.Data;

@Data
public class EventoResponseDTO {

	private String id;
	
	private String titulo;
	
	private String  descricao;
	
	private String tipo;
	
	private String data;
	
	private String organizador;
	
	private Endereco endereco;
	
	@JsonIgnore
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	
	public EventoResponseDTO(Evento evento) {
		this.id = evento.getId();
		this.titulo = evento.getTitulo();
		this.descricao = evento.getDescricao();
		this.tipo = evento.getTipo().toString();
		this.data = evento.getData().format(dtf);
		this.endereco = evento.getEndereco();
		this.organizador = evento.getOrganizador().getNome();
	}

}



