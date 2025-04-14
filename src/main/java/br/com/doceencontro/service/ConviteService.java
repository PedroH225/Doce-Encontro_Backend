package br.com.doceencontro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.doceencontro.model.Convite;
import br.com.doceencontro.repository.ConviteRepository;
import br.com.doceencontro.repository.UsuarioRepository;

@Service
public class ConviteService {

	private ConviteRepository conviteRepository;
	
	private UsuarioService usuarioService;
	
	private EventoService eventoService;

	public ConviteService(ConviteRepository conviteRepository) {
		this.conviteRepository = conviteRepository;
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
}






