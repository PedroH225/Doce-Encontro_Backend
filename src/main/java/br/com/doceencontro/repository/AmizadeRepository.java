package br.com.doceencontro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.doceencontro.model.Amizade;
import br.com.doceencontro.model.StatusAmizade;
import jakarta.transaction.Transactional;

@Repository
public interface AmizadeRepository extends JpaRepository<Amizade, String> {

	@Transactional
	@Modifying
	@Query("DELETE FROM Amizade a WHERE a.id = :amizadeId")
	void excluirAmizade(String amizadeId);
	
	@Query("SELECT a FROM Amizade a "
		     + "WHERE (a.usuario.id = :usuarioId OR a.amigo.id = :usuarioId) "
		     + "AND a.status = 'ACEITO'")
	List<Amizade> buscarAmizades(String usuarioId);
	
	@Query("SELECT a FROM Amizade a "
		     + "WHERE a.usuario.id = :usuarioId "
		     + "AND a.status = 'PENDENTE'")
	List<Amizade> buscarPendentes(String usuarioId);
	
	@Query("SELECT a FROM Amizade a "
		     + "WHERE a.amigo.id = :usuarioId "
		     + "AND a.status = 'PENDENTE'")
	List<Amizade> buscarRecebidos(String usuarioId);
	
	@Query("SELECT a FROM Amizade a "
		     + "WHERE (a.usuario.id = :usuarioId AND a.amigo.id = :amigoId)"
		     + "OR (a.usuario.id = :amigoId AND a.amigo.id = :usuarioId)")
	Optional<Amizade> buscarAmizade(String usuarioId, String amigoId);
	
}
