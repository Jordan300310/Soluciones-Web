package pe.fyj.fyj_erp_api.service.catalogo;

import java.util.List;
import pe.fyj.fyj_erp_api.dto.catalogo.*;

public interface CategoriaService {
  List<CategoriaResponse> listar();
  CategoriaResponse obtener(Long idCategoria);
  CategoriaResponse crear(CategoriaRequest req);
  CategoriaResponse actualizar(Long idCategoria, CategoriaRequest req);
  void eliminar(Long idCategoria);
}