package com.example.festora.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.festora.model.Notificacao;
import com.example.festora.service.NotificacaoService;

@RestController
@RequestMapping("/usuarios/notificacoes")
public class NotificacaoController {

	private NotificacaoService notificacaoService;

	public NotificacaoController(NotificacaoService notificacaoService) {
		this.notificacaoService = notificacaoService;
	}
	
	@GetMapping("/{usuarioId}")
	public List<Notificacao> obterNotificacoesUsuario(@PathVariable String usuarioId) {
		return notificacaoService.obterNotificacoesUsuario(usuarioId);
	}
}


