package pe.fyj.fyj_erp_api.dto.admin;

import java.math.BigDecimal;

public record UpdateEmpleadoRequest(
  String nombres,
  String apat,
  String amat,
  String celular,
  String email,
  String cargo,
  BigDecimal sueldo,
  Boolean estado
) {}