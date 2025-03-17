package com.example.festora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.festora.model.Endereco;
import com.example.festora.model.Evento;
import com.example.festora.model.Tipo;
import com.example.festora.model.Usuario;
import com.example.festora.model.dtos.EventoRequestDTO;
import com.example.festora.model.dtos.EventoResponseDTO;
import com.example.festora.repository.EventoRepository;

@Service
public class EventoService {

	private EventoRepository eventoRepository;
	
	private UsuarioService usuarioService;

	public EventoService(EventoRepository eventoRepository, UsuarioService usuarioService) {
		this.eventoRepository = eventoRepository;
		this.usuarioService = usuarioService;
	}
	
	public List<Evento> obterTodos() {
		return eventoRepository.findAll();
	}
	
	public Evento obterPorId(String id) {
		Optional<Evento> buscarEvento = eventoRepository.findById(id);
		
		if (buscarEvento.isEmpty()) {
			throw new RuntimeException("Evento não encontrado.");
		}
		
		return buscarEvento.get();
	}
	
	public EventoResponseDTO registrarEvento(String organizadorId, EventoRequestDTO eventoDTO) {
		Usuario buscarOrganizador = usuarioService.obterPorId(organizadorId);
		
		Endereco novoEndereco = 
				new Endereco(null, eventoDTO.local(), eventoDTO.estado(), eventoDTO.cidade(), 
						eventoDTO.rua(), eventoDTO.numero());
		
		Evento novoEvento = 
				new Evento(null, eventoDTO.titulo(), eventoDTO.descricao(), Tipo.fromString(eventoDTO.tipo()), 
				eventoDTO.data(), novoEndereco, buscarOrganizador, null, null);
		
		return new EventoResponseDTO(eventoRepository.save(novoEvento));
	}
	
}





