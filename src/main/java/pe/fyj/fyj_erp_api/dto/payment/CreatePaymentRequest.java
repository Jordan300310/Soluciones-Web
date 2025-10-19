package pe.fyj.fyj_erp_api.dto.payment;

import java.math.BigDecimal;

public record CreatePaymentRequest(
  Long id_pedido,
  BigDecimal monto,
  String metodo_pago
) {}