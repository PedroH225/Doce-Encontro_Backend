package com.example.festora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.festora.model.Mensagem;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, String> {

}
