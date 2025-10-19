package pe.fyj.fyj_erp_api.dto.me;

public record UpdateProfileRequest(
  String nombres,
  String apat,
  String amat,
  String celular,
  String email
) {}