package br.com.doceencontro.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.doceencontro.model.dtos.AmigoDTO;
import br.com.doceencontro.service.AmizadeService;
import br.com.doceencontro.utils.IdToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Amizade", description = "Gerenciamento de operações de amizade")
@RestController
@RequestMapping("/usuarios/amizades")
@SecurityRequirement(name = "bearerAuth")
public class AmizadeController {

	private AmizadeService amizadeService;

	public AmizadeController(AmizadeService amizadeService) {
		this.amizadeService = amizadeService;
	}

	@Operation(summary = "Enviar pedido de amizade")
	@PostMapping("/{amigoEmail}")
	public AmigoDTO adicionarAmigo(@PathVariable String amigoEmail) {
		return amizadeService.adicionarAmigo(IdToken.get(), amigoEmail);
	}

	@Operation(summary = "Aceitar pedido de amizade")
	@PutMapping("/{amizadeId}")
	public AmigoDTO aceitarPedido(@PathVariable String amizadeId) {
		return amizadeService.aceitarPedido(IdToken.get(), amizadeId);
	}

	@Operation(summary = "Excluir ou recusar pedido de amizade")
	@DeleteMapping("/{amizadeId}")
	public String excluirAmigo(@PathVariable String amizadeId) {
		return amizadeService.excluirAmigo(IdToken.get(), amizadeId);
	}

	@Operation(summary = "Listar pedidos de amizade recebidos")
	@GetMapping("/recebidos")
	public List<AmigoDTO> buscarRecebidos() {
		return amizadeService.buscarRecebidos(IdToken.get());
	}

	@Operation(summary = "Listar pedidos de amizade enviados que ainda não foram aceitos")
	@GetMapping("/pendentes")
	public List<AmigoDTO> buscarPendentes() {
		return amizadeService.buscarPendentes(IdToken.get());
	}

	@Operation(summary = "Listar amizades aceitas do usuário autenticado")
	@GetMapping
	public List<AmigoDTO> buscarAceitos() {
		return amizadeService.buscarAceitos(IdToken.get());
	}
}
