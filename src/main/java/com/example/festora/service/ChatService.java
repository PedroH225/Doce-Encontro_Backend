package com.example.festora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.festora.model.Chat;
import com.example.festora.repository.ChatRepository;
import com.example.festora.repository.MensagemRepository;

@Service
public class ChatService {

	private ChatRepository chatRepository;

	private MensagemRepository mensagemRepository;

	private EventoService eventoService;

	private UsuarioService usuarioService;

	public ChatService(ChatRepository chatRepository, MensagemRepository mensagemRepository,
			EventoService eventoService, UsuarioService usuarioService) {
		this.chatRepository = chatRepository;
		this.mensagemRepository = mensagemRepository;
		this.eventoService = eventoService;
		this.usuarioService = usuarioService;
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

}
