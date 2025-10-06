package pe.fyj.fyj_erp_api.service.catalog;

import pe.fyj.fyj_erp_api.dto.catalog.ProductUpsertRequest;
import pe.fyj.fyj_erp_api.enums.ProductListRequest;
import pe.fyj.fyj_erp_api.models.catalog.Producto;

import java.util.List;

public interface ProductService {
  List<Producto> list(ProductListRequest request, String q, Boolean estado);
  Producto getOneOrNotFound(Long id);

  Producto create(ProductUpsertRequest req);
  Producto update(Long id, ProductUpsertRequest req);

  void changeEstado(Long id, boolean estado);
  void deleteLogical(Long id);
}