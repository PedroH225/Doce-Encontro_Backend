package com.example.festora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.festora.model.Amizade;
import com.example.festora.repository.AmizadeRepository;

@Service
public class AmizadeService {

	private AmizadeRepository amizadeRepository;

	public AmizadeService(AmizadeRepository amizadeRepository) {
		this.amizadeRepository = amizadeRepository;
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
}




