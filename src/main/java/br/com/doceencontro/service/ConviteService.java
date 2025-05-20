package br.com.doceencontro.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.doceencontro.exception.exceptions.UsuarioNotFoundException;
import br.com.doceencontro.model.Convite;
import br.com.doceencontro.model.Evento;
import br.com.doceencontro.model.Usuario;
import br.com.doceencontro.model.dtos.ConviteDTO;
import br.com.doceencontro.model.dtos.ConviteResponseDTO;
import br.com.doceencontro.model.dtos.UsuarioResponseDTO;
import br.com.doceencontro.repository.ConviteRepository;
import br.com.doceencontro.utils.ConversorDTO;
import br.com.doceencontro.utils.EventoUtils;
import br.com.doceencontro.utils.IdToken;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConviteService {

	private ConviteRepository conviteRepository;

	private UsuarioService usuarioService;

	private EventoService eventoService;

	public List<Convite> findAll() {
		return conviteRepository.findAll();
	}

	public Convite findById(String id) {
		Optional<Convite> buscarConvite = conviteRepository.findById(id);

		if (buscarConvite.isEmpty()) {
			throw new RuntimeException("Convite não encontrado.");
		}

		return buscarConvite.get();
	}

	private List<ConviteResponseDTO> converterDtos(List<Convite> convites) {
		return convites.stream().map(c -> new ConviteResponseDTO(c)).collect(Collectors.toList());
	}

	public ConviteDTO convidar(List<String> usuariosIds, String eventoId) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Evento evento = eventoService.findById(eventoId);
		
		EventoUtils.garantirAutoria(evento, IdToken.get());

		Set<String> idsParticipantes = evento.getParticipantes().stream().map(Usuario::getId)
				.collect(Collectors.toSet());
		
		Set<String> idsConvidados = evento.getConvite().getDestinatarios().stream().map(Usuario::getId)
				.collect(Collectors.toSet());
		
		String autorId = evento.getOrganizador().getId();

	    for (String usuarioId : usuariosIds) {
	        if (!idsParticipantes.contains(usuarioId) && !idsConvidados.contains(usuarioId)
	            && !usuarioId.equals(autorId)) {
	        	
	            try {
	                usuarios.add(usuarioService.findById(usuarioId));
	                
	            } catch (UsuarioNotFoundException e) {}
	        }
	    }

		evento.getConvite().enviarConvite(usuarios);

		Convite convite = this.eventoService.salvar(evento).getConvite();
		
		convite.setDestinatarios(new HashSet<Usuario>(usuarios));

		return ConversorDTO.convite(convite);
	}

	public List<ConviteResponseDTO> listarConvitesUsuario(String destinatarioId) {
		return converterDtos(conviteRepository.findByDestinatariosId(destinatarioId));
	}

	public List<UsuarioResponseDTO> buscarConvidados(String eventoId) {
		return ConversorDTO.usuariosSet(eventoService.findById(eventoId).getConvite().getDestinatarios());
	}

	public String removerConvite(String eventoId, String usuarioId) {
		Evento evento = eventoService.findById(eventoId);

		EventoUtils.garantirAutoria(evento, IdToken.get());

		EventoUtils.garantirConvidado(evento, usuarioId);

		evento.getConvite().removerDestPorId(usuarioId);

		eventoService.salvar(evento);

		return "Convite removido com sucesso!";
	}
}
