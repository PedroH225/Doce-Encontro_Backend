package br.com.doceencontro.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.doceencontro.model.dtos.ConviteDTO;
import br.com.doceencontro.service.ConviteService;

@RestController
@RequestMapping("/eventos/convites")
public class ConviteController {

	private ConviteService conviteService;

	public ConviteController(ConviteService conviteService) {
		this.conviteService = conviteService;
	}
	
	@PostMapping("/{eventoId}")
	public ConviteDTO convidarParticipantes(@PathVariable String eventoId, @RequestBody List<String> usuariosIds) {
		return conviteService.convidar(usuariosIds, eventoId);
	}
}



