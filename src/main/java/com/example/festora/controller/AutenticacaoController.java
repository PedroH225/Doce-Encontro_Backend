package com.example.festora.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.festora.model.dtos.AuthetinticationDTO;
import com.example.festora.model.dtos.RegisterDTO;
import com.example.festora.service.AuthorizationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class AutenticacaoController {

	private AuthorizationService authorizationService;

	public AutenticacaoController(AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<Object> register(@RequestBody RegisterDTO registerDto ) {
		return authorizationService.register(registerDto);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody @Valid AuthetinticationDTO data) {
		return authorizationService.login(data);
	}
}
