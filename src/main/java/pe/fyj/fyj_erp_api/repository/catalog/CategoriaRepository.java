package pe.fyj.fyj_erp_api.repository.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.fyj.fyj_erp_api.models.catalog.Categoria;
import java.util.List;
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByEstadoTrueOrderByNombreAsc();
}