package pe.fyj.fyj_erp_api.repository.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.fyj.fyj_erp_api.models.catalog.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
  Page<Producto> findByEstadoTrue(Pageable p);
  Page<Producto> findByEstadoTrueAndIdMarca(Long idMarca, Pageable p);
  Page<Producto> findByEstadoTrueAndIdCategoria(Long idCategoria, Pageable p);
  Page<Producto> findByEstadoTrueAndIdMarcaAndIdCategoria(Long idMarca, Long idCategoria, Pageable p);
}