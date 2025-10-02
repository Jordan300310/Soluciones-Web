package pe.fyj.fyj_erp_api.dto.producto;

import java.math.BigDecimal;
import pe.fyj.fyj_erp_api.entity.catalogo.Categoria;
import pe.fyj.fyj_erp_api.entity.catalogo.Marca;
import pe.fyj.fyj_erp_api.entity.producto.Producto;
import static pe.fyj.fyj_erp_api.shared.Estados.normalizeEstado;

public record ProductoRequest(
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
  public Producto toEntity(Categoria cat, Marca mar) {
    var p = new Producto();
    p.setCategoria(cat);
    p.setMarca(mar);
    p.setCodProducto(codProducto);
    p.setNomProducto(nomProducto);
    p.setDesProducto(desProducto);
    p.setPcoProducto(pcoProducto);
    p.setPveProducto(pveProducto);
    p.setStkProducto(stkProducto);
    p.setEstProducto(normalizeEstado(estProducto, null));
    return p;
  }

  public void apply(Producto p, Categoria cat, Marca mar) {
    p.setCategoria(cat);
    p.setMarca(mar);
    p.setCodProducto(codProducto);
    p.setNomProducto(nomProducto);
    p.setDesProducto(desProducto);
    p.setPcoProducto(pcoProducto);
    p.setPveProducto(pveProducto);
    p.setStkProducto(stkProducto);
    p.setEstProducto(normalizeEstado(estProducto, p.getEstProducto()));
  }
}