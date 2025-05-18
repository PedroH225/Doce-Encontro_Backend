package br.com.doceencontro.service;

import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.doceencontro.model.Icone;
import br.com.doceencontro.model.Notificacao;
import br.com.doceencontro.model.Usuario;
import br.com.doceencontro.model.dtos.EventoResponseDTO;
import br.com.doceencontro.model.dtos.NotificacaoResponseDTO;
import br.com.doceencontro.repository.NotificacaoRepository;
import br.com.doceencontro.utils.ConversorDTO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NotificacaoService {
	
	private NotificacaoRepository notificacaoRepository;
	
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
	
	public List<NotificacaoResponseDTO> obterNotificacoesUsuario(String usuarioId) {
		return ConversorDTO.notificacoes(notificacaoRepository.findAllByUsuariosId(usuarioId));
	}
}



