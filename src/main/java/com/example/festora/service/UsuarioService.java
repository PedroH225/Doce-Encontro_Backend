package com.example.festora.service;

import java.util.List;
import java.util.Optional;

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
	
	public Usuario obterPorId(String id) {
		Optional<Usuario> buscarUsuario = usuarioRepository.findById(id);
		
		if (buscarUsuario.isEmpty()) {
			throw new RuntimeException("Usuário não encontrado.");
		}
		
		return buscarUsuario.get();
	}
	
	public Usuario registrarUsuário(Usuario usuario) {
		Usuario usuariodb = usuarioRepository.save(usuario);
		
		return usuariodb;
	}

	public Usuario editarUsuario(String id, Usuario usuario) {
		Usuario buscarUsuario = obterPorId(id);
		
		usuario.setId(buscarUsuario.getId());
		return usuarioRepository.save(usuario);
	}

	public String excluirUsuario(String id) {
		Usuario buscarUsuario = obterPorId(id);
		
		usuarioRepository.delete(buscarUsuario);
		
		return "Usuário excluído com sucesso.";
	}
	
	
}
