package pe.fyj.fyj_erp_api.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.fyj.fyj_erp_api.models.auth.Empleado;

import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
  Optional<Empleado> findByPersonaId(Long idPersona);
}