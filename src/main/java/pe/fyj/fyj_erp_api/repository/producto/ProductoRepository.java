package pe.fyj.fyj_erp_api.repository.producto;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.fyj.fyj_erp_api.entity.producto.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> { }