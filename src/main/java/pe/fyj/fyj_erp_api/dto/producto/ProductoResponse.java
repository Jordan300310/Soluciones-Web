package pe.fyj.fyj_erp_api.dto.producto;

import java.math.BigDecimal;
import pe.fyj.fyj_erp_api.entity.producto.Producto;

public record ProductoResponse(
    Long idProducto,
    Long idCategoria,
    Long idMarca,
    String codProducto,
    String nomProducto,
    String desProducto,
    BigDecimal pcoProducto,
    BigDecimal pveProducto,
    BigDecimal stkProducto,
    Integer estProducto
) {
  public static ProductoResponse from(Producto p) {
    return new ProductoResponse(
        p.getIdProducto(),
        p.getCategoria().getIdCategoria(),
        p.getMarca().getIdMarca(),
        p.getCodProducto(),
        p.getNomProducto(),
        p.getDesProducto(),
        p.getPcoProducto(),
        p.getPveProducto(),
        p.getStkProducto(),
        p.getEstProducto()
    );
  }
}