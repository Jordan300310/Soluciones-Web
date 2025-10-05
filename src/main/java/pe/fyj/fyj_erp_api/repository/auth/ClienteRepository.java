package pe.fyj.fyj_erp_api.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.fyj.fyj_erp_api.models.auth.Cliente;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
  Optional<Cliente> findByPersonaId(Long idPersona);
}