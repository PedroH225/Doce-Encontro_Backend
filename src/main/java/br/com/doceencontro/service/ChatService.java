package br.com.doceencontro.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.doceencontro.exception.exceptions.ChatNotFoundException;
import br.com.doceencontro.model.Chat;
import br.com.doceencontro.model.Mensagem;
import br.com.doceencontro.model.Usuario;
import br.com.doceencontro.model.dtos.MensagemDTO;
import br.com.doceencontro.repository.ChatRepository;
import br.com.doceencontro.repository.EventoRepository;
import br.com.doceencontro.repository.MensagemRepository;

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
			throw new ChatNotFoundException();
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

		if (!eventoService.verificarAutor(usuarioId, buscarChat.getEvento())) {
			eventoService.garantirParticipacao(buscarChat.getEvento(), usuarioId);
		}

		Mensagem novaMensagem = new Mensagem(buscarChat, buscarUsuario, mensagem);

		return new MensagemDTO(mensagemRepository.save(novaMensagem));
	}

	public List<MensagemDTO> obterMensagens(String chatId) {
		findById(chatId);
		
		return converterDTOs(mensagemRepository.obterMensagensPorData(chatId));
	}

}
