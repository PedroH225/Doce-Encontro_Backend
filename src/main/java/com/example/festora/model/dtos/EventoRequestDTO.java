package com.example.festora.model.dtos;

import java.time.LocalDateTime;

public record EventoRequestDTO(String titulo, String descricao, String tipo, LocalDateTime data, 
		String local, String estado, String cidade, String rua, Integer numero) {

}
