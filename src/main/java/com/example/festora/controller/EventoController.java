package com.example.festora.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.festora.model.Evento;
import com.example.festora.service.EventoService;


@RestController
@RequestMapping("/eventos")
public class EventoController {

	private EventoService eventoService;

	public EventoController(EventoService eventoService) {
		this.eventoService = eventoService;
	}
	
	@GetMapping
	public List<Evento> obterTodos() {
		return eventoService.obterTodos();
	}
	
}
