package br.com.doceencontro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.doceencontro.model.Notificacao;
import br.com.doceencontro.model.Usuario;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, String>{

	public List<Notificacao> findAllByUsuariosId(String usuarioId);
	
	@Query("SELECT DISTINCT p FROM Evento e " +
		       "JOIN e.participantes p " +
		       "LEFT JOIN FETCH p.notificacoes " +
		       "WHERE e.id = :id")
		List<Usuario> findParticipantesComNotificacoes(@Param("id") String id);


}
