package pe.fyj.fyj_erp_api.dto.me;

import java.time.LocalDate;

public record ProfileResponse(
  Long id_persona,
  String nombres,
  String apat,
  String amat,
  String dni,
  String celular,
  String email,
  LocalDate fechaNacimiento,
  Boolean cliente_activo
) {}