package br.com.doceencontro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.doceencontro.model.Usuario;
import jakarta.transaction.Transactional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Usuario u WHERE u.id = :id")
	void excluir(String id);
	
	UserDetails findByEmail(String email);
	
	Optional<Usuario> findByEmailAndIdNot(String email, String id);
	
	@Query("SELECT u FROM Usuario u WHERE u.email = :email")
	Optional<Usuario> buscarPorEmail(String email);

}
