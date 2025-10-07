package pe.fyj.fyj_erp_api.dto.catalog;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import pe.fyj.fyj_erp_api.models.catalog.Producto;

public record ProductUpsertRequest(
    @NotBlank @Size(max = 150) String nombre,
    String descripcion,
    @NotNull @DecimalMin(value = "0.0", inclusive = true) BigDecimal precio,
    @NotNull @Min(0) Integer stock,
    Boolean estado,
    Long proveedorId 
) {
  public Producto toNewEntityWithProveedorId(Long provId) {
    var p = new Producto();
    applyTo(p);
    if (this.estado == null) {
      p.setEstado(true); 
    }
    p.setIdProveedor(provId);
    return p;
  }
  public void applyTo(Producto p) {
    if (nombre != null) p.setNombre(nombre);
    if (descripcion != null) p.setDescripcion(descripcion);
    if (precio != null) p.setPrecio(precio);
    if (stock != null) p.setStock(stock);
    if (estado != null) p.setEstado(estado);
  }
  public void applyProveedorId(Producto p, Long provId) {
    p.setIdProveedor(provId);
  }
}