package br.com.doceencontro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.doceencontro.model.Convite;

@Repository
public interface ConviteRepository extends JpaRepository<Convite, String> {

}
