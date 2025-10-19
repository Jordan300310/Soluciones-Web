package pe.fyj.fyj_erp_api.repository.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.fyj.fyj_erp_api.models.payment.EstadoPago;

import java.util.Optional;

public interface EstadoPagoRepository extends JpaRepository<EstadoPago, Long> {
  Optional<EstadoPago> findByNombreEstadoIgnoreCase(String nombreEstado);
}