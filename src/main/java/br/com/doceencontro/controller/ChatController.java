package br.com.doceencontro.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.doceencontro.model.dtos.MensagemDTO;
import br.com.doceencontro.service.ChatService;
import br.com.doceencontro.utils.IdToken;

@RestController
@RequestMapping("/eventos/chats")
public class ChatController {

	private ChatService chatService;

	public ChatController(ChatService chatService) {
		this.chatService = chatService;
	}
	
	@PostMapping("/mensagens/{chatId}")
	public MensagemDTO enviarMensagem(@PathVariable String chatId, @RequestBody String mensagem) {
		
		return chatService.enviarMensagem(mensagem, chatId, IdToken.get());
	}
	
	@GetMapping("/mensagens/{chatId}")
	public List<MensagemDTO> obterMensagens(@PathVariable String chatId) {
		return chatService.obterMensagens(chatId);
	}
}





