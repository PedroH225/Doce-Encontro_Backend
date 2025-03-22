package com.example.festora.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.festora.model.Evento;
import com.example.festora.model.Requisito;
import com.example.festora.repository.RequisitoRepository;

@Service
public class RequisitoService {

	private RequisitoRepository repository;
	
	private EventoService eventoService;

	public RequisitoService(RequisitoRepository repository, EventoService eventoService) {
		this.eventoService = eventoService;
		this.repository = repository;
	}
	
	public Requisito findById(String id) {
		Optional<Requisito> buscarRequisito = repository.findById(id);
		
		if (buscarRequisito.isEmpty()) {
			throw new RuntimeException("Requisito não encontrado.");
		}
		
		return buscarRequisito.get();
	}
	
	public Requisito criarRequisito(Requisito requisito, String eventoId) {
		Evento buscarEvento = eventoService.findById(eventoId);
		
		buscarEvento.addRequisito(requisito);
		
		return repository.save(requisito);
	}
	
}
