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

@RestController
@RequestMapping("/usuarios/amizades")
public class AmizadeController {

	private AmizadeService amizadeService;

	public AmizadeController(AmizadeService amizadeService) {
		this.amizadeService = amizadeService;
	}
	
	@PostMapping("/{amigoEmail}")
	public AmigoDTO adicionarAmigo(@PathVariable String amigoEmail) {
		return amizadeService.adicionarAmigo(IdToken.get() ,amigoEmail);
	}
	
	@PutMapping("/{amizadeId}")
	public AmigoDTO aceitarPedido(@PathVariable String amizadeId) {
		return amizadeService.aceitarPedido(IdToken.get(), amizadeId);
	}
	
	@DeleteMapping("/{amizadeId}")
	public String excluirAmigo(@PathVariable String amizadeId) {
		return amizadeService.excluirAmigo(IdToken.get(), amizadeId);
	}
	
	@GetMapping("/recebidos")
	public List<AmigoDTO> buscarRecebidos() {
		return amizadeService.buscarRecebidos(IdToken.get());
	}
	
	@GetMapping("/pendentes")
	public List<AmigoDTO> buscarPendentes() {
		return amizadeService.buscarPendentes(IdToken.get());
	}
	
	@GetMapping
	public List<AmigoDTO> buscarAceitos() {
		return amizadeService.buscarAceitos(IdToken.get());
	}
	
}



