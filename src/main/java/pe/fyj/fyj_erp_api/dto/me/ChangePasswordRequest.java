package pe.fyj.fyj_erp_api.dto.me;

public record ChangePasswordRequest(
  String passwordActual,
  String passwordNueva
) {}