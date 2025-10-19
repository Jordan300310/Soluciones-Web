package pe.fyj.fyj_erp_api.dto.purchase;

import java.math.BigDecimal;
import java.util.List;

public record CreatePurchaseRequest(
  Long id_proveedor,
  List<Item> items
) {
  public record Item(Long id_producto, Integer cantidad, BigDecimal precio_unit) {}
}