package com.example.festora.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.festora.exception.exceptions.EventoNotFoundException;
import com.example.festora.model.Chat;
import com.example.festora.model.Endereco;
import com.example.festora.model.Evento;
import com.example.festora.model.Tipo;
import com.example.festora.model.Usuario;
import com.example.festora.model.dtos.EventoDetailsDTO;
import com.example.festora.model.dtos.EventoRequestDTO;
import com.example.festora.model.dtos.EventoResponseDTO;
import com.example.festora.repository.EnderecoRepository;
import com.example.festora.repository.EventoRepository;

import jakarta.transaction.Transactional;

@Service
public class EventoService {

	private EventoRepository eventoRepository;

	private EnderecoRepository enderecoRepository;

	private UsuarioService usuarioService;

	public EventoService(EventoRepository eventoRepository, UsuarioService usuarioService,
			EnderecoRepository enderecoRepository) {
		this.eventoRepository = eventoRepository;
		this.usuarioService = usuarioService;
		this.enderecoRepository = enderecoRepository;
	}

	public List<Evento> findAll() {
		return eventoRepository.findAll();
	}

	public Evento findById(String id) {
		Optional<Evento> buscarEvento = eventoRepository.findById(id);

		if (buscarEvento.isEmpty()) {
			throw new EventoNotFoundException();
		}

		return buscarEvento.get();
	}

	public void garantirNaoParticipacao(String eventoId, String usuarioId) {
		if (eventoRepository.verificarParticipacao(usuarioId, eventoId).isPresent()) {
			throw new RuntimeException("Você já está participando do evento");
		}

	}
	
	public void garantirParticipacao(String eventoId, String usuarioId) {
		if (eventoRepository.verificarParticipacao(usuarioId, eventoId).isEmpty()) {
			throw new RuntimeException("Você não está participando do evento");
		}
	}
	
	public boolean verificarAutor(String usuarioId, String eventoId) {
		return eventoRepository.findAutor(usuarioId, eventoId).isPresent();
	}

	private EventoDetailsDTO converterParticipantesDto(Evento evento) {
		return new EventoDetailsDTO(evento);
	}

	private EventoResponseDTO converterDto(Evento evento) {
		return new EventoResponseDTO(evento);
	}

	private List<EventoResponseDTO> converterDtos(List<Evento> evento) {
		return evento.stream().map(e -> new EventoResponseDTO(e)).collect(Collectors.toList());
	}

	public List<EventoResponseDTO> obterTodos() {
		return converterDtos(eventoRepository.findAll());
	}

	public EventoDetailsDTO obterPorId(String id) {
		Evento buscarEvento = findById(id);

		return converterParticipantesDto(buscarEvento);
	}

	public EventoResponseDTO registrarEvento(String organizadorId, EventoRequestDTO eventoDTO) {
		Usuario buscarOrganizador = usuarioService.findById(organizadorId);

		Endereco novoEndereco = new Endereco(null, eventoDTO.local(), eventoDTO.estado(), eventoDTO.cidade(),
				eventoDTO.rua(), eventoDTO.numero(), null);

		Evento novoEvento = new Evento(null, eventoDTO.titulo(), eventoDTO.descricao(),
				Tipo.fromString(eventoDTO.tipo()), eventoDTO.data(), novoEndereco, buscarOrganizador, null, null, null,
				null);

		novoEndereco.setEvento(novoEvento);
		novoEvento.setChat(new Chat(novoEvento));

		return new EventoResponseDTO(eventoRepository.save(novoEvento));
	}

	public EventoResponseDTO editarEvento(String eventoId, EventoRequestDTO eventoDTO, String autorId) {
		if (!verificarAutor(autorId, eventoId)) {
			throw new RuntimeException("Você não é o autor do evento.");
		}
		Evento buscarEvento = findById(eventoId);

		Evento eventoEditado = eventoRepository.save(buscarEvento.editar(eventoDTO));

		return converterDto(eventoEditado);
	}

	@Transactional
	public String excluirEvento(String eventoId, String autorId) {
		if (!verificarAutor(autorId, eventoId)) {
			throw new RuntimeException("Você não é o autor do evento.");
		}
		
		Evento buscarEvento = findById(eventoId);

		eventoRepository.excluir(buscarEvento.getId());
		enderecoRepository.excluir(buscarEvento.getEndereco().getId());

		return "Evento excluído com sucesso.";
	}

	public String participar(String eventoId, String usuarioId) {
		Evento buscarEvento = findById(eventoId);
		Usuario buscarUsuario = usuarioService.findById(usuarioId);
		garantirNaoParticipacao(eventoId, usuarioId);

		if (verificarAutor(usuarioId, eventoId)) {
			throw new RuntimeException("Você já está participando do evento.");
		}

		buscarEvento.addParticipante(buscarUsuario);

		buscarEvento.getChat().adicionarParticipante(buscarUsuario);

		eventoRepository.save(buscarEvento);

		return "Usuário adicionado com sucesso";
	}

	public String removerParticipacao(String eventoId, String usuarioId) {
		Evento buscarEvento = findById(eventoId);
		Usuario buscarUsuario = usuarioService.findById(usuarioId);
		
		if (verificarAutor(usuarioId, eventoId)) {
			throw new RuntimeException("Organizadores não podem retirar a participação.");
		}
		garantirParticipacao(eventoId, usuarioId);
		
		
		buscarEvento.removerParticipante(buscarUsuario);

		buscarEvento.getChat().removerParticipante(buscarUsuario);

		eventoRepository.save(buscarEvento);

		return "Participação removida com sucesso.";
	}

}
