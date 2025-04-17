package br.com.doceencontro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.doceencontro.model.Convite;

@Repository
public interface ConviteRepository extends JpaRepository<Convite, String> {

	List<Convite> findByDestinatariosId(String usuarioId);
}
