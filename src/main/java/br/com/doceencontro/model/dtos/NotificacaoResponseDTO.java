package br.com.doceencontro.model.dtos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.doceencontro.model.Notificacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacaoResponseDTO {
	private String id;
	
	private String titulo;
	
	private String corpo;
	
	private String data;
	
	private String icone;
	
	@JsonIgnore
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm");

	public NotificacaoResponseDTO(Notificacao notificacao) {
		this.id = notificacao.getId();
		this.titulo = notificacao.getTitulo();
		this.corpo = notificacao.getCorpo();
		this.data = notificacao.getData().format(dtf);
		this.icone = notificacao.getIcone().toString();
	}
}
