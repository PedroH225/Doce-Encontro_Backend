package com.example.festora.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.festora.model.Evento;
import com.example.festora.repository.EventoRepository;

@Service
public class EventoService {

	private EventoRepository eventoRepository;
	
	private UsuarioService usuarioService;

	public EventoService(EventoRepository eventoRepository) {
		this.eventoRepository = eventoRepository;
	}
	
	public List<Evento> obterTodos() {
		return eventoRepository.findAll();
	}
	
	
}
