package pe.fyj.fyj_erp_api.dto.admin;

public record UpdateProveedorRequest(
  String nombres,
  String apat,
  String amat,
  String celular,
  String email,
  String razonSocial,
  String ruc,
  Boolean estado
) {}