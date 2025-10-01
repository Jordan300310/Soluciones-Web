package pe.fyj.fyj_erp_api.repository.persona;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.fyj.fyj_erp_api.entity.persona.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
