package br.com.doceencontro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.doceencontro.model.dtos.AuthetinticationDTO;
import br.com.doceencontro.model.dtos.RegisterDTO;
import br.com.doceencontro.service.AuthorizationService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class AutenticacaoController {

	private AuthorizationService authorizationService;

	public AutenticacaoController(AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<Object> register(@RequestBody @Valid RegisterDTO registerDto ) {
		return authorizationService.register(registerDto);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody @Valid AuthetinticationDTO data) {
		return authorizationService.login(data);
	}
}
