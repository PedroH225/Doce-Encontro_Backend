package br.com.doceencontro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.doceencontro.model.Convite;
import br.com.doceencontro.model.Evento;
import br.com.doceencontro.model.Usuario;
import br.com.doceencontro.model.dtos.ConviteDTO;
import br.com.doceencontro.repository.ConviteRepository;
import br.com.doceencontro.repository.EventoRepository;

@Service
public class ConviteService {

	private ConviteRepository conviteRepository;

	private EventoRepository eventoRepository;

	private UsuarioService usuarioService;

	private EventoService eventoService;

	public ConviteService(ConviteRepository conviteRepository, EventoService eventoService,
			UsuarioService usuarioService, EventoRepository eventoRepository) {
		this.conviteRepository = conviteRepository;
		this.usuarioService = usuarioService;
		this.eventoService = eventoService;
		this.eventoRepository = eventoRepository;
	}

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

	private ConviteDTO converterDto(Convite convite) {
		return new ConviteDTO(convite);
	}

	public ConviteDTO convidar(List<String> usuariosIds, String eventoId) {
		List<Usuario> usuarios = new ArrayList<Usuario>();

		Evento evento = eventoService.findById(eventoId);

		usuariosIds.forEach(id -> {
			usuarios.add(usuarioService.findById(id));
		});
		
		evento.getConvite().enviarConvite(usuarios);

		Evento eventodb = this.eventoRepository.save(evento);
		
		return converterDto(eventodb.getConvite());
	}
}
