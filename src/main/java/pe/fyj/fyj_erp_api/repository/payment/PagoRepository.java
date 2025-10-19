package pe.fyj.fyj_erp_api.repository.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.fyj.fyj_erp_api.models.payment.Pago;
import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Long> {
     List<Pago> findByIdPedidoIn(List<Long> idsPedido); 
}