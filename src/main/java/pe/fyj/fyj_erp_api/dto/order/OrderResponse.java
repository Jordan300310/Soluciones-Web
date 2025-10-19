package pe.fyj.fyj_erp_api.dto.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
  Long id_pedido,
  LocalDateTime fecha_pedido,
  String estado,
  BigDecimal total,
  List<Detalle> detalles
) {
  public record Detalle(Long id_producto, String nombre, BigDecimal precio, Integer cantidad, BigDecimal subtotal) {}
}