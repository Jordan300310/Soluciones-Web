package pe.fyj.fyj_erp_api.dto.admin;

public record UpdateClienteRequest(
  String nombres,
  String apat,
  String amat,
  String celular,
  String email,
  Boolean estado
) {}