package pe.fyj.fyj_erp_api.dto.catalog;

import java.math.BigDecimal;
import pe.fyj.fyj_erp_api.models.catalog.Producto;

public record ProductAdminDTO(
    Long id, 
    String nombre, 
    String descripcion, 
    BigDecimal precio, 
    Integer stock, 
    Boolean estado
) {
  public static ProductAdminDTO from(Producto p) {
    return new ProductAdminDTO(
        p.getId(), 
        p.getNombre(), 
        p.getDescripcion(),               
        p.getPrecio(), 
        p.getStock(), 
        p.getEstado());
  }
}