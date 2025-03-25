package com.example.festora.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.festora.model.Mensagem;
import com.example.festora.model.dtos.MensagemDTO;
import com.example.festora.service.ChatService;

@RestController
@RequestMapping("/eventos/chats")
public class ChatController {

	private ChatService chatService;

	public ChatController(ChatService chatService) {
		this.chatService = chatService;
	}
	
	@PostMapping("/mensagens/{chatId}/{usuarioId}")
	public MensagemDTO enviarMensagem(@PathVariable String chatId, @PathVariable String usuarioId,
			@RequestBody String mensagem) {
		
		return chatService.enviarMensagem(mensagem, chatId, usuarioId);
	}
}





