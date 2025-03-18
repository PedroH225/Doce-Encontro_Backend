package com.example.festora.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.festora.model.Evento;
import com.example.festora.model.dtos.EventoRequestDTO;
import com.example.festora.model.dtos.EventoResponseDTO;
import com.example.festora.service.EventoService;


@RestController
@RequestMapping("/eventos")
public class EventoController {

	private EventoService eventoService;

	public EventoController(EventoService eventoService) {
		this.eventoService = eventoService;
	}
	
	@GetMapping
	public List<EventoResponseDTO> obterTodos() {
		return eventoService.obterTodos();
	}
	
	@GetMapping("/{id}")
	public EventoResponseDTO obterPorId(@PathVariable String id) {
		return eventoService.obterPorId(id);
	}
	
	@PostMapping("/{organizadorId}")
	public EventoResponseDTO registrarEvento(@PathVariable String organizadorId, @RequestBody EventoRequestDTO eventoDTO) {
		return eventoService.registrarEvento(organizadorId, eventoDTO);
	}
}





