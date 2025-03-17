package com.example.festora.service;

import org.springframework.stereotype.Service;

import com.example.festora.repository.EventoRepository;

@Service
public class EventoService {

	private EventoRepository eventoRepository;
	
	private UsuarioService usuarioService;

	public EventoService(EventoRepository eventoRepository) {
		this.eventoRepository = eventoRepository;
	}
	
	
}
