package com.example.festora.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.festora.model.Requisito;
import com.example.festora.repository.RequisitoRepository;
import com.example.festora.service.RequisitoService;

@RestController
@RequestMapping("/eventos/requisitos")
public class RequisitoController {

	private RequisitoService service;

	public RequisitoController(RequisitoService service) {
		this.service = service;
	}
	
	@PostMapping("/{eventoId}")
	public Requisito criarRequisito(@RequestBody Requisito requisito, @PathVariable String eventoId) {
		return service.criarRequisito(requisito, eventoId);
	}
	
}
