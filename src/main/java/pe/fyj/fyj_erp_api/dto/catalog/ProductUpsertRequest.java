package pe.fyj.fyj_erp_api.dto.catalog;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import pe.fyj.fyj_erp_api.models.catalog.Producto;

public record ProductUpsertRequest(
    @NotBlank @Size(max = 150) String nombre,
    String descripcion,
    @NotNull @DecimalMin(value = "0.0", inclusive = true) BigDecimal precio,
    @NotNull @Min(0) Integer stock,
    Boolean estado
) {
  public Producto toNewEntity() {
    var p = new Producto();
    applyTo(p);
    if (p.getEstado() == null) p.setEstado(Boolean.TRUE);
    return p;
  }
  public void applyTo(Producto p) {
    p.setNombre(nombre);
    p.setDescripcion(descripcion);
    p.setPrecio(precio);
    p.setStock(stock);
    if (estado != null) p.setEstado(estado);
  }
}