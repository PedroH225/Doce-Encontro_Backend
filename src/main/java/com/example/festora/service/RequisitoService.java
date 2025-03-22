package com.example.festora.service;

import org.springframework.stereotype.Service;

import com.example.festora.model.Evento;
import com.example.festora.model.Requisito;
import com.example.festora.model.dtos.EventoRequestDTO;
import com.example.festora.model.dtos.EventoResponseDTO;
import com.example.festora.repository.RequisitoRepository;

@Service
public class RequisitoService {

	private RequisitoRepository repository;
	
	private EventoService eventoService;

	public RequisitoService(RequisitoRepository repository, EventoService eventoService) {
		this.eventoService = eventoService;
		this.repository = repository;
	}
	
	public Requisito criarRequisito(Requisito requisito, String eventoId) {
		Evento buscarEvento = eventoService.findById(eventoId);
		
		buscarEvento.addRequisito(requisito);
		
		return repository.save(requisito);
	}
}
