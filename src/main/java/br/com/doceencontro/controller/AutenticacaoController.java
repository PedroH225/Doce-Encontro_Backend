package br.com.doceencontro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.doceencontro.model.dtos.AuthetinticationDTO;
import br.com.doceencontro.model.dtos.RegisterDTO;
import br.com.doceencontro.service.AuthorizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(name = "Autenticação", description = "Autenticação do usuário no sistema")
@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class AutenticacaoController {

	private AuthorizationService authorizationService;
	
	@Operation(summary = "Verificar a validade do token")
	@GetMapping("/verificarToken")
	public ResponseEntity<Void> verificarToken() {
		return ResponseEntity.ok().build();
	}
	
	@Operation(summary = "Fazer registro")
	@PostMapping("/registrar")
	public ResponseEntity<Object> register(@RequestBody @Valid RegisterDTO registerDto ) {
		return authorizationService.register(registerDto);
	}
	
	@Operation(summary = "Fazer login")
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody @Valid AuthetinticationDTO data) {
		return authorizationService.login(data);
	}
}
