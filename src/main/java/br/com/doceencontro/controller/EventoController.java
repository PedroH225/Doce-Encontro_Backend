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

import br.com.doceencontro.model.Evento;
import br.com.doceencontro.model.dtos.EventoDetailsDTO;
import br.com.doceencontro.model.dtos.EventoRequestDTO;
import br.com.doceencontro.model.dtos.EventoResponseDTO;
import br.com.doceencontro.model.dtos.UsuarioResponseDTO;
import br.com.doceencontro.service.EventoService;
import br.com.doceencontro.utils.EventoUtils;
import br.com.doceencontro.utils.IdToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(name = "Evento", description = "Gerenciamento de operações do evento")
@RestController
@RequestMapping("/eventos")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class EventoController {

	private EventoService eventoService;

	@Operation(summary = "Listar todos os eventos")
	@GetMapping
	public List<EventoResponseDTO> obterTodos() {
		return eventoService.obterTodos();
	}

	@Operation(summary = "Obter evento por ID")
	@GetMapping("/{id}")
	public EventoDetailsDTO obterPorId(@PathVariable String id) {
		return eventoService.obterPorId(id);
	}

	@Operation(summary = "Listar eventos ativos do usuário autenticado")
	@GetMapping("/ativos")
	public List<EventoResponseDTO> listarAtivos() {
		return eventoService.listarAtivos(IdToken.get());
	}
	
	@Operation(summary = "Listar eventos passados do usuário autenticado")
	@GetMapping("/passados")
	public List<EventoResponseDTO> listarPassados() {
		return eventoService.listarPassados(IdToken.get());
	}
	
	@Operation(summary = "Listar eventos criados pelo usuário autenticado")
	@GetMapping("/autor")
	public List<EventoResponseDTO> listarEventosDoUsuario() {
		return eventoService.listarDoUsuario(IdToken.get());
	}
	
	@Operation(summary = "Listar tipos de eventos disponíveis")
	@GetMapping("/tipos")
	public List<String> tiposDeEvento() {
		return eventoService.tiposDeEvento();
	}
	
	@Operation(summary = "Listar participantes de um evento")
	@GetMapping("/participantes/{eventoId}")
	public List<UsuarioResponseDTO> listarParticipantes(@PathVariable String eventoId) {
		return eventoService.buscarParticipantes(eventoId);
	}

	@Operation(summary = "Registrar um novo evento")
	@PostMapping
	public EventoResponseDTO registrarEvento(@RequestBody @Valid EventoRequestDTO eventoDTO) {
		return eventoService.registrarEvento(IdToken.get(), eventoDTO);
	}

	@Operation(summary = "Editar um evento existente")
	@PutMapping("/{eventoId}")
	public EventoResponseDTO editarEvento(@PathVariable String eventoId, @RequestBody @Valid EventoRequestDTO eventoDTO) {
		return eventoService.editarEvento(eventoId, eventoDTO, IdToken.get());
	}

	@Operation(summary = "Excluir um evento")
	@DeleteMapping("/{eventoId}")
	public String excluirEvento(@PathVariable String eventoId) {
		return eventoService.excluirEvento(eventoId, IdToken.get());
	}

	@Operation(summary = "Participar de um evento")
	@PostMapping("/participar/{eventoId}")
	public String participar(@PathVariable String eventoId) {
		return eventoService.participar(eventoId, IdToken.get());
	}

	@Operation(summary = "Remover a própria participação de um evento")
	@DeleteMapping("/participar/{eventoId}")
	public String removerParticipacao(@PathVariable String eventoId) {
		return eventoService.removerParticipacao(eventoId, IdToken.get());
	}
	
	@Operation(summary = "Remover um participante específico de um evento")
	@DeleteMapping("/retirarParticipante/{eventoId}/{usuarioId}")
	public String retirarParticipacao(@PathVariable String eventoId, @PathVariable String usuarioId) {
		return eventoService.removerParticipacao(eventoId, usuarioId);
	}

	@Operation(summary = "Desativar um evento")
	@PutMapping("/{eventoId}/desativar")
	public String desativarEvento(@PathVariable String eventoId) {
		return eventoService.desativarEvento(eventoId, IdToken.get());
	}

	@Operation(summary = "Verificar se o usuário autenticado é autor do evento")
	@GetMapping("/{eventoId}/verificar-autor")
	public boolean verificarAutor(@PathVariable String eventoId) {
		String usuarioId = IdToken.get();
		Evento evento = eventoService.findById(eventoId);
		return EventoUtils.verificarAutor(usuarioId, evento);
	}
}
