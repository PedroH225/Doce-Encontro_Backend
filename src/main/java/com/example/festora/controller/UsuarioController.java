package com.example.festora.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.festora.model.Usuario;
import com.example.festora.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@GetMapping
	public List<Usuario> obterTodos() {
		return usuarioService.obterTodos();
	}
	
	@GetMapping("/{id}")
	public Usuario obterPorId(@PathVariable String id) {
		return usuarioService.obterPorId(id);
	}
	
	@PostMapping
	public Usuario registrarUsuario(@RequestBody Usuario usuario) {
		return usuarioService.registrarUsuário(usuario);
	}
	
	
	
}
