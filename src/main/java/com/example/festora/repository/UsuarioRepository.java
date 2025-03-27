package com.example.festora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.example.festora.model.Usuario;

import jakarta.transaction.Transactional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Usuario u WHERE u.id = :id")
	void excluir(String id);
	
	UserDetails findByEmail(String email);
}
