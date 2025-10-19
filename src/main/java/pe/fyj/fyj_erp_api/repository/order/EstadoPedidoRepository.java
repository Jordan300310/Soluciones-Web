package pe.fyj.fyj_erp_api.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.fyj.fyj_erp_api.models.order.EstadoPedido;

import java.util.Optional;

public interface EstadoPedidoRepository extends JpaRepository<EstadoPedido, Long> {
  Optional<EstadoPedido> findByNombreEstadoIgnoreCase(String nombreEstado);
}