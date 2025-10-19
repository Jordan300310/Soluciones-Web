package pe.fyj.fyj_erp_api.repository.purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.fyj.fyj_erp_api.models.purchase.EstadoCompra;

import java.util.Optional;

public interface EstadoCompraRepository extends JpaRepository<EstadoCompra, Long> {
  Optional<EstadoCompra> findByNombreEstadoIgnoreCase(String nombreEstado);
}