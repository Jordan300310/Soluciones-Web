package pe.fyj.fyj_erp_api.service.producto;

import java.util.List;
import pe.fyj.fyj_erp_api.dto.producto.*;

public interface ProductoService {
  List<ProductoResponse> listar();
  ProductoResponse obtener(Long idProducto);
  ProductoResponse crear(ProductoRequest req);
  ProductoResponse actualizar(Long idProducto, ProductoRequest req);
  void eliminar(Long idProducto);
}