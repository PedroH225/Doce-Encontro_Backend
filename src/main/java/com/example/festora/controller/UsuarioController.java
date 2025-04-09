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
import com.example.festora.model.dtos.UsuarioDetailsDTO;
import com.example.festora.model.dtos.UsuarioResponseDTO;
import com.example.festora.service.UsuarioService;
import com.example.festora.utils.IdToken;

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
	
	@GetMapping("/find")
	public UsuarioDetailsDTO obterPorId() {
		return usuarioService.obterPorId(IdToken.get());
	}
	
	@PutMapping
	public UsuarioDetailsDTO editarUsuario(@RequestBody Usuario usuario) {
		return usuarioService.editarUsuario(IdToken.get(), usuario);
	}
	
	@DeleteMapping
	public String excluirUsuario() {
		return usuarioService.excluirUsuario(IdToken.get());
	}
	
	
	
}
