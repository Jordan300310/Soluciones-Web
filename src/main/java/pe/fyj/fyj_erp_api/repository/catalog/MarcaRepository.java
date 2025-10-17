package pe.fyj.fyj_erp_api.repository.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.fyj.fyj_erp_api.models.catalog.Marca;
import java.util.List;
public interface MarcaRepository extends JpaRepository<Marca, Long> {
    List<Marca> findByEstadoTrueOrderByNombreAsc();
}