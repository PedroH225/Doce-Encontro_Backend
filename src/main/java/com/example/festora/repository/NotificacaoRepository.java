package com.example.festora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.festora.model.Notificacao;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, String>{

}
