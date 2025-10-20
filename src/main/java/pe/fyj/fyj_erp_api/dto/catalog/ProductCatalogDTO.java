package pe.fyj.fyj_erp_api.dto.catalog;

import java.math.BigDecimal;
import pe.fyj.fyj_erp_api.models.catalog.Producto;

public record ProductCatalogDTO(
    Long id,
    String nombre,
    String descripcion,
    BigDecimal precio,
    Integer stock,
    String imagen,
    Ref marca,
    Ref categoria
) {
  public static ProductCatalogDTO from(Producto p) {
    Ref m = (p.getMarca()!=null)     ? new Ref(p.getMarca().getId(),     p.getMarca().getNombre())     : null;
    Ref c = (p.getCategoria()!=null) ? new Ref(p.getCategoria().getId(), p.getCategoria().getNombre()) : null;
    return new ProductCatalogDTO(
        p.getId(), p.getNombre(), p.getDescripcion(),
        p.getPrecio(), p.getStock(),p.getImagen(), m, c
    );
  }

  public static record Ref(Long id, String nombre) {}
}