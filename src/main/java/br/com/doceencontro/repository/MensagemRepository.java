package br.com.doceencontro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.doceencontro.model.Mensagem;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, String> {

	@Query("SELECT m FROM Mensagem m JOIN m.chat c WHERE c.id = :chatId ORDER BY m.dataEnvio")
	List<Mensagem> obterMensagensPorData(String chatId);
}
