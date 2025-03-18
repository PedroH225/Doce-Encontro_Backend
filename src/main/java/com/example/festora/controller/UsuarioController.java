package com.example.festora.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.festora.model.Usuario;
import com.example.festora.model.dtos.UsuarioResponseDTO;
import com.example.festora.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@GetMapping
	public List<UsuarioResponseDTO> obterTodos() {
		return usuarioService.obterTodos();
	}
	
	@GetMapping("/{id}")
	public UsuarioResponseDTO obterPorId(@PathVariable String id) {
		return usuarioService.obterPorId(id);
	}
	
	@PostMapping
	public UsuarioResponseDTO registrarUsuario(@RequestBody Usuario usuario) {
		return usuarioService.registrarUsuário(usuario);
	}
	
	@PutMapping("/{id}")
	public UsuarioResponseDTO editarUsuario(@PathVariable String id, @RequestBody Usuario usuario) {
		return usuarioService.editarUsuario(id, usuario);
	}
	
	@DeleteMapping("/{id}")
	public String excluirUsuario(@PathVariable String id) {
		return usuarioService.excluirUsuario(id);
	}
	
	
	
}
