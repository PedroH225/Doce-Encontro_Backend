package com.example.festora.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.festora.model.Chat;
import com.example.festora.model.Mensagem;
import com.example.festora.model.Usuario;
import com.example.festora.model.dtos.MensagemDTO;
import com.example.festora.repository.ChatRepository;
import com.example.festora.repository.EventoRepository;
import com.example.festora.repository.MensagemRepository;

@Service
public class ChatService {

	private ChatRepository chatRepository;

	private MensagemRepository mensagemRepository;

	private EventoRepository eventoRepository;

	private EventoService eventoService;

	private UsuarioService usuarioService;

	public ChatService(ChatRepository chatRepository, MensagemRepository mensagemRepository,
			EventoRepository eventoRepository, EventoService eventoService, UsuarioService usuarioService) {
		this.chatRepository = chatRepository;
		this.mensagemRepository = mensagemRepository;
		this.eventoService = eventoService;
		this.usuarioService = usuarioService;
		this.eventoRepository = eventoRepository;
	}

	public Chat findById(String id) {
		Optional<Chat> buscarChat = chatRepository.findById(id);

		if (buscarChat.isEmpty()) {
			throw new RuntimeException("Chat não encontrado.");
		}

		return buscarChat.get();
	}

	public List<Chat> findByAll() {
		return chatRepository.findAll();
	}
	
	private List<MensagemDTO> converterDTOs(List<Mensagem> mensagens) {
		return mensagens.stream()
				.map(m -> new MensagemDTO(m))
				.collect(Collectors.toList());
	}

	public MensagemDTO enviarMensagem(String mensagem, String chatId, String usuarioId) {
		Chat buscarChat = findById(chatId);
		Usuario buscarUsuario = usuarioService.findById(usuarioId);

		String eventoId = buscarChat.getEvento().getId();
		if (!eventoService.verificarAutor(usuarioId, eventoId)) {
			eventoService.garantirParticipacao(eventoId, usuarioId);
		}

		Mensagem novaMensagem = new Mensagem(buscarChat, buscarUsuario, mensagem);

		return new MensagemDTO(mensagemRepository.save(novaMensagem));
	}

	public List<MensagemDTO> obterMensagens(String chatId) {
		return converterDTOs(mensagemRepository.obterMensagensPorData(chatId));
	}

}
