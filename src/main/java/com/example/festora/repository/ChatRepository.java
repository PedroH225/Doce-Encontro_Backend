package com.example.festora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.festora.model.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, String>{

}
