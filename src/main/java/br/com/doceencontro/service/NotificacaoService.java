package br.com.doceencontro.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.doceencontro.model.Notificacao;
import br.com.doceencontro.model.Usuario;
import br.com.doceencontro.model.dtos.EventoResponseDTO;
import br.com.doceencontro.model.dtos.NotificacaoResponseDTO;
import br.com.doceencontro.model.dtos.UsuarioResponseDTO;
import br.com.doceencontro.repository.NotificacaoRepository;
import br.com.doceencontro.utils.ConversorDTO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NotificacaoService {

	private NotificacaoRepository notificacaoRepository;
	
	private UsuarioService usuarioService;

	public List<Usuario> findParticipantesComNotificacao(EventoResponseDTO evento) {
		return notificacaoRepository.findParticipantesComNotificacoes(evento.getId());
	}

	@Async
	public void notificarParticipantes(EventoResponseDTO evento, List<Usuario> participantes, Notificacao notificacao) {
		for (Usuario participante : participantes) {
			if (!participante.getNome().equalsIgnoreCase(evento.getOrganizador())) {
				notificacao.getUsuarios().add(participante);
				participante.getNotificacoes().add(notificacao);
			}
		}

		notificacaoRepository.save(notificacao);
	}

	@Async
	public void notificarUsuarios(List<UsuarioResponseDTO> usuariosDTO, Notificacao notificacao) {
		List<Usuario> usuarios = usuariosDTO.stream()
				.map(u -> usuarioService.findById(u.getId()))
				.collect(Collectors.toList());
		
		notificacao.setUsuarios(usuarios);
		
		notificacaoRepository.save(notificacao);
	}

	public List<NotificacaoResponseDTO> obterNotificacoesUsuario(String usuarioId) {
		return ConversorDTO.notificacoes(notificacaoRepository.findAllByUsuariosId(usuarioId));
	}

	public void notificarUsuario(UsuarioResponseDTO amigo, Notificacao novaNotificacao) {
		Usuario usuario = usuarioService.findById(amigo.getId());
		
		novaNotificacao.getUsuarios().add(usuario);
		
		notificacaoRepository.save(novaNotificacao);
	}
}
