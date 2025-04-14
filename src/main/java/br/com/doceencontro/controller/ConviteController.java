package br.com.doceencontro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.doceencontro.service.ConviteService;

@RestController
@RequestMapping("/eventos/convites")
public class ConviteController {

	private ConviteService conviteService;

	public ConviteController(ConviteService conviteService) {
		this.conviteService = conviteService;
	}
	
	
}



