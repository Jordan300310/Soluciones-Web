package pe.fyj.fyj_erp_api.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.fyj.fyj_erp_api.models.auth.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    boolean existsByDniIgnoreCase(String dni);
}