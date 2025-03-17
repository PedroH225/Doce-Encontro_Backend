package com.example.festora.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.festora.model.Usuario;
import com.example.festora.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public List<Usuario> obterTodos() {
		return usuarioRepository.findAll();
	}
	
	
}
