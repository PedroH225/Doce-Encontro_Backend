package br.com.doceencontro.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.doceencontro.model.Requisito;
import br.com.doceencontro.model.dtos.RequisitoResponseDTO;
import br.com.doceencontro.service.RequisitoService;
import br.com.doceencontro.utils.IdToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Presentes", description = "Gerenciamento de presentes dos eventos")
@RestController
@RequestMapping("/eventos/presentes")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class RequisitoController {

	private RequisitoService service;

	@Operation(summary = "Listar presentes de um evento")
	@GetMapping("/{eventoId}")
	public List<RequisitoResponseDTO> buscarRequisitos(@PathVariable String eventoId) {
		return service.buscarRequisitos(eventoId);
	}

	@Operation(summary = "Criar presente para um evento")
	@PostMapping("/{eventoId}")
	public RequisitoResponseDTO criarRequisito(@RequestBody Requisito requisito, @PathVariable String eventoId) {
		return service.criarRequisito(requisito, eventoId);
	}

	@Operation(summary = "Editar presente existente")
	@PutMapping("/{requisitoId}")
	public RequisitoResponseDTO editarRequisito(@RequestBody Requisito requisito, @PathVariable String requisitoId) {
		return service.editarRequisito(requisito, requisitoId);
	}

	@Operation(summary = "Excluir presente")
	@DeleteMapping("/{requisitoId}")
	public String excluirRequisito(@PathVariable String requisitoId) {
		return service.excluirRequisito(requisitoId);
	}

	@Operation(summary = "Adicionar responsável por um presente")
	@PostMapping("/addResp/{requisitoId}")
	public RequisitoResponseDTO adicionarResponsavel(@PathVariable String requisitoId) {
		return service.addResponsavel(requisitoId, IdToken.get());
	}

	@Operation(summary = "Remover responsável de um presente")
	@DeleteMapping("/removerResp/{requisitoId}")
	public RequisitoResponseDTO removerResponsavel(@PathVariable String requisitoId) {
		return service.removerResponsavel(requisitoId, IdToken.get());
	}
}
