package pe.fyj.fyj_erp_api.repository.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.fyj.fyj_erp_api.models.catalog.Producto;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
  List<Producto> findByEstadoTrue();
  List<Producto> findByEstado(Boolean estado);
}