package pe.fyj.fyj_erp_api.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.fyj.fyj_erp_api.models.order.Pedido;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
  List<Pedido> findByIdCliente(Long idCliente);
}