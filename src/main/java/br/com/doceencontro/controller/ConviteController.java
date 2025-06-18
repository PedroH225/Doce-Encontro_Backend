package br.com.doceencontro.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.doceencontro.model.dtos.ConviteDTO;
import br.com.doceencontro.model.dtos.UsuarioResponseDTO;
import br.com.doceencontro.service.ConviteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Convites", description = "Gerenciamento de convites dos eventos")
@RestController
@RequestMapping("/eventos/convites")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class ConviteController {

	private ConviteService conviteService;

	@Operation(summary = "Buscar todos os convidados de um evento")
	@GetMapping("/{eventoId}")
	public List<UsuarioResponseDTO> buscarConvidados(@PathVariable String eventoId) {
		return conviteService.buscarConvidados(eventoId);
	}

	@Operation(summary = "Convidar usuários para um evento")
	@PostMapping("/{eventoId}")
	public ConviteDTO convidarParticipantes(@PathVariable String eventoId, @RequestBody List<String> usuariosIds) {
		return conviteService.convidar(usuariosIds, eventoId);
	}

	@Operation(summary = "Remover um convidado de um evento")
	@DeleteMapping("/{eventoId}/{usuarioId}")
	public String removerConvidado(@PathVariable String eventoId, @PathVariable String usuarioId) {
		return conviteService.removerConvite(eventoId, usuarioId);
	}
}




