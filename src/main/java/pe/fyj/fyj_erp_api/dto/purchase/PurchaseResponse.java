package pe.fyj.fyj_erp_api.dto.purchase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PurchaseResponse(
  Long id_compra,
  LocalDateTime fecha_compra,
  String estado,
  BigDecimal total,
  List<Detalle> detalles
) {
  public record Detalle(Long id_producto, Integer cantidad, BigDecimal subtotal) {}
}