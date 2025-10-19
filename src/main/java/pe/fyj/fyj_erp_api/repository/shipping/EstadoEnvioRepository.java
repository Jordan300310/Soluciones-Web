package pe.fyj.fyj_erp_api.repository.shipping;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.fyj.fyj_erp_api.models.shipping.EstadoEnvio;

import java.util.Optional;

public interface EstadoEnvioRepository extends JpaRepository<EstadoEnvio, Long> {
  Optional<EstadoEnvio> findByNombreEstadoIgnoreCase(String nombreEstado);
}