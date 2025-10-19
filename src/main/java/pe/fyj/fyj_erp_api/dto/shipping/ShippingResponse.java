package pe.fyj.fyj_erp_api.dto.shipping;

import java.time.LocalDateTime;

public record ShippingResponse(
  Long id_envio,
  Long id_pedido,
  String direccion_entrega,
  LocalDateTime fecha_envio,
  String estado
) {}