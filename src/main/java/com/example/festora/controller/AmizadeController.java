package com.example.festora.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.example.festora.model.Amizade;
import com.example.festora.model.dtos.AmizadeResponseDTO;
import com.example.festora.service.AmizadeService;
import com.example.festora.utils.IdToken;

@RestController
@RequestMapping("/usuarios/amizades")
public class AmizadeController {

	private AmizadeService amizadeService;

	public AmizadeController(AmizadeService amizadeService) {
		this.amizadeService = amizadeService;
	}
	
	@PostMapping("/{amigoId}")
	public AmizadeResponseDTO adicionarAmigo(@PathVariable String amigoId) {
		return amizadeService.adicionarAmigo(IdToken.get() ,amigoId);
	}
	
	@PutMapping("/{amizadeId}")
	public AmizadeResponseDTO aceitarPedido(@PathVariable String amizadeId) {
		return amizadeService.aceitarPedido(amizadeId);
	}
	
	@DeleteMapping("/{amizadeId}")
	public String excluirAmigo(@PathVariable String amizadeId) {
		return amizadeService.excluirAmigo(amizadeId);
	}
}



