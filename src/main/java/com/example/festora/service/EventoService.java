package com.example.festora.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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
			throw new RuntimeException("Evento não encontrado.");
		}

		return buscarEvento.get();
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
		Optional<Evento> buscarEvento = eventoRepository.findById(id);

		if (buscarEvento.isEmpty()) {
			throw new RuntimeException("Evento não encontrado.");
		}

		return converterParticipantesDto(buscarEvento.get());
	}

	public EventoResponseDTO registrarEvento(String organizadorId, EventoRequestDTO eventoDTO) {
		Usuario buscarOrganizador = usuarioService.findById(organizadorId);

		Endereco novoEndereco = new Endereco(null, eventoDTO.local(), eventoDTO.estado(), eventoDTO.cidade(),
				eventoDTO.rua(), eventoDTO.numero(), null);

		Evento novoEvento = new Evento(null, eventoDTO.titulo(), eventoDTO.descricao(),
				Tipo.fromString(eventoDTO.tipo()), eventoDTO.data(), novoEndereco, buscarOrganizador,
				null, null, null, null);

		novoEndereco.setEvento(novoEvento);
		novoEvento.setChat(new Chat(novoEvento));

		return new EventoResponseDTO(eventoRepository.save(novoEvento));
	}

	public EventoResponseDTO editarEvento(String eventoId, EventoRequestDTO eventoDTO) {
		Evento buscarEvento = findById(eventoId);

		Evento eventoEditado = eventoRepository.save(buscarEvento.editar(eventoDTO));

		return converterDto(eventoEditado);
	}

	@Transactional
	public String excluirEvento(String id) {
		Evento buscarEvento = findById(id);

		eventoRepository.excluir(buscarEvento.getId());
		enderecoRepository.excluir(buscarEvento.getEndereco().getId());

		return "Evento excluído com sucesso.";
	}

	public String participar(String eventoId, String usuarioId) {
		Optional<Usuario> usuario = eventoRepository.verificarParticipacao(usuarioId, eventoId);
		Evento buscarEvento = findById(eventoId);
		Usuario buscarUsuario = usuarioService.findById(usuarioId);

		if (usuario.isPresent() || buscarEvento.getOrganizador() == buscarUsuario) {
			throw new RuntimeException("Você já está participando do evento.");
		}

		buscarEvento.addParticipante(buscarUsuario);

		eventoRepository.save(buscarEvento);

		return "Usuário adicionado com sucesso";
	}

	public String removerParticipacao(String eventoId, String usuarioId) {
		Evento buscarEvento = findById(eventoId);
		Usuario buscarUsuario = usuarioService.findById(usuarioId);

		if (buscarEvento.getOrganizador() == buscarUsuario) {
			throw new RuntimeException("Organizadores não podem retirar a participação.");
		}

		Optional<Usuario> usuario = eventoRepository.verificarParticipacao(usuarioId, eventoId);

		if (usuario.isEmpty()) {
			throw new RuntimeException("Você não está participando do evento.");
		}

		buscarEvento.removerParticipante(buscarUsuario);

		eventoRepository.save(buscarEvento);

		return "Participação removida com sucesso.";
	}

}
