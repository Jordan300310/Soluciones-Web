package pe.fyj.fyj_erp_api.repository.catalogo;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.fyj.fyj_erp_api.entity.catalogo.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {}