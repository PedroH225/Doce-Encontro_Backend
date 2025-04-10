package com.example.festora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.festora.model.Amizade;
import com.example.festora.model.StatusAmizade;

import jakarta.transaction.Transactional;

@Repository
public interface AmizadeRepository extends JpaRepository<Amizade, String> {

	@Transactional
	@Modifying
	@Query("DELETE FROM Amizade a WHERE a.id = :amizadeId")
	void excluirAmizade(String amizadeId);
	
	@Query("SELECT a FROM Amizade a "
		     + "WHERE (a.usuario.id = :usuarioId OR a.amigo.id = :usuarioId) "
		     + "AND a.status = :status")
	List<Amizade> buscarAmizades(String usuarioId, StatusAmizade status);
}
