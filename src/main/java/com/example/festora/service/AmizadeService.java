package com.example.festora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.festora.exception.exceptions.UsuarioNotFoundException;
import com.example.festora.model.Amizade;
import com.example.festora.model.StatusAmizade;
import com.example.festora.model.Usuario;
import com.example.festora.model.dtos.AmizadeResponseDTO;
import com.example.festora.repository.AmizadeRepository;

@Service
public class AmizadeService {

	private AmizadeRepository amizadeRepository;

	private UsuarioService usuarioService;

	public AmizadeService(AmizadeRepository amizadeRepository, UsuarioService usuarioService) {
		this.amizadeRepository = amizadeRepository;
		this.usuarioService = usuarioService;
	}

	public List<Amizade> findAll() {
		return amizadeRepository.findAll();
	}

	public Amizade findById(String amizadeId) {
		Optional<Amizade> buscarAmizade = amizadeRepository.findById(amizadeId);

		if (buscarAmizade.isEmpty()) {
			throw new RuntimeException("Amizade não encontrada.");
		}

		return buscarAmizade.get();
	}

	private AmizadeResponseDTO converterDto(Amizade amizade) {
		return new AmizadeResponseDTO(amizade);
	}

	public AmizadeResponseDTO adicionarAmigo(String usuarioId, String amigoId) {
		Usuario usuario = usuarioService.findById(usuarioId);
		try {
			Usuario amigo = usuarioService.findById(amigoId);
			Amizade novaAmizade = new Amizade(null, usuario, amigo, StatusAmizade.PENDENTE);

			novaAmizade.addAmigo(usuario, amigo);

			return converterDto(amizadeRepository.save(novaAmizade));
		} catch (UsuarioNotFoundException e) {
			throw new RuntimeException("Amigo não encontrado.");
		}
	}
	
	public AmizadeResponseDTO aceitarPedido(String amizadeId) {
		Amizade amizade = findById(amizadeId);
		
		amizade.setStatus(StatusAmizade.ACEITO);
		
		return converterDto(amizadeRepository.save(amizade));
	}
}



