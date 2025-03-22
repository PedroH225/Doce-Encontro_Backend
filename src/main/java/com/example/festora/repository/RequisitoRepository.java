package com.example.festora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.festora.model.Requisito;

import jakarta.transaction.Transactional;

public interface RequisitoRepository extends JpaRepository<Requisito, String>{

	@Transactional
	@Modifying
	@Query("DELETE FROM Requisito r WHERE r.id = :id")
	void excluir(String id);
	
}
