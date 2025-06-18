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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Chats", description = "Gerenciamento de chats dos eventos")
@RestController
@RequestMapping("/eventos/chats")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class ChatController {

	private ChatService chatService;

	@Operation(summary = "Enviar uma mensagem em um chat de evento")
	@PostMapping("/mensagens/{chatId}")
	public MensagemDTO enviarMensagem(@PathVariable String chatId, @RequestBody String mensagem) {
		return chatService.enviarMensagem(mensagem, chatId, IdToken.get());
	}

	@Operation(summary = "Obter mensagens de um chat de evento")
	@GetMapping("/mensagens/{chatId}")
	public List<MensagemDTO> obterMensagens(@PathVariable String chatId) {
		return chatService.obterMensagens(chatId);
	}
}
	
