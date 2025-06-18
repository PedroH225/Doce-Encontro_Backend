package br.com.doceencontro.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.doceencontro.model.Usuario;
import br.com.doceencontro.model.dtos.ConviteResponseDTO;
import br.com.doceencontro.model.dtos.EventoResponseDTO;
import br.com.doceencontro.model.dtos.UsuarioDetailsDTO;
import br.com.doceencontro.model.dtos.UsuarioResponseDTO;
import br.com.doceencontro.service.ConviteService;
import br.com.doceencontro.service.UsuarioService;
import br.com.doceencontro.utils.IdToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Usuário", description = "Gerenciamento de operações do usuário")
@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class UsuarioController {

	private UsuarioService usuarioService;
	private ConviteService conviteService;

	@Operation(summary = "Obter todos os usuários")
	@GetMapping
	public List<UsuarioResponseDTO> obterTodos() {
		return usuarioService.obterTodos();
	}

	@Operation(summary = "Obter dados do usuário autenticado")
	@GetMapping("/find")
	public UsuarioDetailsDTO obterPorId() {
		return usuarioService.obterPorId(IdToken.get());
	}

	@Operation(summary = "Listar convites do usuário autenticado")
	@GetMapping("/convites")
	public List<ConviteResponseDTO> listarConvitesUsuario() {
		return conviteService.listarConvitesUsuario(IdToken.get());
	}

	@Operation(summary = "Listar eventos criados pelo usuário")
	@GetMapping("/criados")
	public List<EventoResponseDTO> listarEventosCriados() {
		return usuarioService.listarEventosCriados(IdToken.get());
	}

	@Operation(summary = "Listar eventos participados pelo usuário")
	@GetMapping("/participados")
	public List<EventoResponseDTO> listarEventosParticipados() {
		return usuarioService.listarEventosParticipados(IdToken.get());
	}

	@Operation(summary = "Verificar se o usuário participa de um evento")
	@GetMapping("/isParticipando/{eventoId}")
	public ResponseEntity<Void> verificarParticipacao(@PathVariable String eventoId) {
		return usuarioService.verificarParticipacao(eventoId, IdToken.get());
	}

	@Operation(summary = "Editar dados do usuário")
	@PutMapping
	public UsuarioDetailsDTO editarUsuario(@RequestBody Usuario usuario) {
		return usuarioService.editarUsuario(IdToken.get(), usuario);
	}

	@Operation(summary = "Excluir usuário autenticado")
	@DeleteMapping
	public String excluirUsuario() {
		return usuarioService.excluirUsuario(IdToken.get());
	}

	@Operation(summary = "Negar convite por ID")
	@DeleteMapping("/convites/{conviteId}")
	public String negarConvite(@PathVariable String conviteId) {
		return conviteService.negarConvite(conviteId, IdToken.get());
	}
}
