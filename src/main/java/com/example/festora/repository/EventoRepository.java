package com.example.festora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.festora.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, String> {

}
