package com.example.festora.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.festora.service.AmizadeService;

@RestController
@RequestMapping("/usuarios/amizades")
public class AmizadeController {

	private AmizadeService amizadeService;

	public AmizadeController(AmizadeService amizadeService) {
		this.amizadeService = amizadeService;
	}
	
}
