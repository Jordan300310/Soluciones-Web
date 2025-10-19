package pe.fyj.fyj_erp_api.dto.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentResponse(
  Long id_pago,
  Long id_pedido,
  BigDecimal monto,
  String metodo_pago,
  LocalDateTime fecha_pago,
  String estado
) {}