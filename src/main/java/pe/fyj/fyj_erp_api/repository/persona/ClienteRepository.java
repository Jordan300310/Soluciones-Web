package pe.fyj.fyj_erp_api.repository.persona;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.fyj.fyj_erp_api.entity.persona.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {}
