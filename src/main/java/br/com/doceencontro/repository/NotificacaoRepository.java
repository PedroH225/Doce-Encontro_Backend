package br.com.doceencontro.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.doceencontro.model.Notificacao;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, String>{

	public List<Notificacao> findAllByUsuariosId(String usuarioId);
}
