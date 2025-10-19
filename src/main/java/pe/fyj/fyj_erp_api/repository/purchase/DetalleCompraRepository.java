package pe.fyj.fyj_erp_api.repository.purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.fyj.fyj_erp_api.models.purchase.DetalleCompra;

import java.util.List;

public interface DetalleCompraRepository extends JpaRepository<DetalleCompra, Long> {
  List<DetalleCompra> findByIdCompra(Long idCompra);
}