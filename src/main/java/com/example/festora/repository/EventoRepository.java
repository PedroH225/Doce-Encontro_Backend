package com.example.festora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.festora.model.Evento;

import jakarta.transaction.Transactional;

@Repository
public interface EventoRepository extends JpaRepository<Evento, String> {
	
	@Modifying
    @Transactional
	@Query("DELETE FROM Evento e WHERE e.id = :id")
	void excluir(String id);

}
