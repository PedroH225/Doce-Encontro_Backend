package com.example.festora.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.festora.model.Evento;
import com.example.festora.model.Notificacao;
import com.example.festora.model.Usuario;
import com.example.festora.model.dtos.UsuarioResponseDTO;
import com.example.festora.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	private UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	public Usuario findById(String id) {
		Optional<Usuario> buscarUsuario = usuarioRepository.findById(id);

		if (buscarUsuario.isEmpty()) {
			throw new RuntimeException("Usuário não encontrado.");
		}

		return buscarUsuario.get();
	}

	private List<UsuarioResponseDTO> converterDtos(List<Usuario> usuario) {
		return usuario.stream().map(u -> new UsuarioResponseDTO(u)).collect(Collectors.toList());
	}

	private UsuarioResponseDTO converterDto(Usuario usuario) {
		return new UsuarioResponseDTO(usuario);
	}

	public List<UsuarioResponseDTO> obterTodos() {
		return converterDtos(usuarioRepository.findAll());
	}

	public UsuarioResponseDTO obterPorId(String id) {
		Optional<Usuario> buscarUsuario = usuarioRepository.findById(id);

		if (buscarUsuario.isEmpty()) {
			throw new RuntimeException("Usuário não encontrado.");
		}

		return converterDto(buscarUsuario.get());
	}

	public UsuarioResponseDTO registrarUsuário(Usuario usuario) {
		Usuario usuariodb = usuarioRepository.save(usuario);

		return converterDto(usuariodb);
	}

	public UsuarioResponseDTO editarUsuario(String id, Usuario usuario) {
		Usuario buscarUsuario = findById(id);

		usuario.setId(buscarUsuario.getId());
		return converterDto(usuarioRepository.save(usuario));
	}

	@Transactional
	public String excluirUsuario(String id) {
		Usuario buscarUsuario = findById(id);

		usuarioRepository.excluir(buscarUsuario.getId());

		return "Usuário excluído com sucesso.";
	}

}
