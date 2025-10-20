package pe.fyj.fyj_erp_api.dto.admin;

import java.math.BigDecimal;
import pe.fyj.fyj_erp_api.models.catalog.Producto;

public record ProductAdminDTO(
    Long id,
    String nombre,
    String descripcion,
    BigDecimal precio,
    Integer stock,
    String imagen,
    Boolean estado,
    Ref marca,
    Ref categoria,
    Ref proveedor
) {
  public static ProductAdminDTO from(Producto p) {
    Ref m = (p.getMarca()!=null)     ? new Ref(p.getMarca().getId(),     p.getMarca().getNombre())     : null;
    Ref c = (p.getCategoria()!=null) ? new Ref(p.getCategoria().getId(), p.getCategoria().getNombre()) : null;
    Ref prov = null;
    if (p.getProveedor()!=null) {
      String nombreProv = (p.getProveedor().getRazonSocial()!=null && !p.getProveedor().getRazonSocial().isBlank())
          ? p.getProveedor().getRazonSocial()
          : (p.getProveedor().getPersona()!=null ? p.getProveedor().getPersona().getNombres() : null);
      prov = new Ref(p.getProveedor().getId(), nombreProv);
    }
    return new ProductAdminDTO(
        p.getId(), p.getNombre(), p.getDescripcion(),
        p.getPrecio(), p.getStock(),p.getImagen(), p.getEstado(),
        m, c, prov
    );
  }

  public static record Ref(Long id, String nombre) {}
}