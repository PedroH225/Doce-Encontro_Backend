package br.com.doceencontro.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.doceencontro.model.Notificacao;
import br.com.doceencontro.model.dtos.NotificacaoResponseDTO;
import br.com.doceencontro.service.NotificacaoService;
import br.com.doceencontro.utils.IdToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Notificações", description = "Gerenciamento de notificações")
@RestController
@RequestMapping("/usuarios/notificacoes")
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class NotificacaoController {

	private NotificacaoService notificacaoService;
	
	@Operation(summary = "Obter notificações do usuário")
	@GetMapping
	public List<NotificacaoResponseDTO> obterNotificacoesUsuario() {
		return notificacaoService.obterNotificacoesUsuario(IdToken.get());
	}
}


