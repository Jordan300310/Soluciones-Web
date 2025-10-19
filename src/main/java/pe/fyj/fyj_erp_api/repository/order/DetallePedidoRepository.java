package pe.fyj.fyj_erp_api.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.fyj.fyj_erp_api.models.order.DetallePedido;
import java.util.List;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {
  List<DetallePedido> findByIdPedido(Long idPedido);
}