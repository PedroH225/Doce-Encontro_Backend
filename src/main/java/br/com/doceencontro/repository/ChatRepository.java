package br.com.doceencontro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.doceencontro.model.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, String>{

}
