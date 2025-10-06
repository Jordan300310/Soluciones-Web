package pe.fyj.fyj_erp_api.dto.catalog;

import java.math.BigDecimal;
import pe.fyj.fyj_erp_api.models.catalog.Producto;

public record ProductDTO(
    Long id, 
    String nombre, 
    String descripcion, 
    BigDecimal precio, 
    Integer stock
) {
  public static ProductDTO from(Producto p) {
    return new ProductDTO(p.getId(), 
    p.getNombre(), 
    p.getDescripcion(),
    p.getPrecio(), 
    p.getStock());
  }
}