package com.example.festora.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.festora.model.dtos.EventoDetailsDTO;
import com.example.festora.model.dtos.EventoRequestDTO;
import com.example.festora.model.dtos.EventoResponseDTO;
import com.example.festora.service.EventoService;
import com.example.festora.utils.IdToken;

import jakarta.validation.Valid;


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
	public EventoDetailsDTO obterPorId(@PathVariable String id) {
		return eventoService.obterPorId(id);
	}
	
	@PostMapping
	public EventoResponseDTO registrarEvento(@RequestBody @Valid EventoRequestDTO eventoDTO) {
		return eventoService.registrarEvento(IdToken.get(), eventoDTO);
	}
	
	@PutMapping("/{eventoId}")
	public EventoResponseDTO editarEvento(@PathVariable String eventoId, @RequestBody @Valid EventoRequestDTO eventoDTO) {
		return eventoService.editarEvento(eventoId, eventoDTO, IdToken.get());
	}
	
	@DeleteMapping("/{eventoId}")
	public String excluirEvento(@PathVariable String eventoId) {
		return eventoService.excluirEvento(eventoId, IdToken.get());
	}
	
	@PostMapping("/participar/{eventoId}")
	public String participar(@PathVariable String eventoId) {
		return eventoService.participar(eventoId, IdToken.get());
	}
	
	@DeleteMapping("/participar/{eventoId}")
	public String removerParticipacao(@PathVariable String eventoId) {
		return eventoService.removerParticipacao(eventoId, IdToken.get());
	}
	
}





