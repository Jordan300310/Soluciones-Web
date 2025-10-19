package pe.fyj.fyj_erp_api.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.fyj.fyj_erp_api.models.auth.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  Optional<Usuario> findByUsernameIgnoreCase(String username);
  Optional<Usuario> findByPersonaId(Long idPersona);
}