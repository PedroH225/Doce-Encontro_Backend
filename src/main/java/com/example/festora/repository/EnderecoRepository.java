package com.example.festora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.festora.model.Endereco;

import jakarta.transaction.Transactional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, String> {

	@Transactional
	@Modifying
	@Query("DELETE FROM Endereco e WHERE e.id = :id")
	void excluir(String id);
	
}
