package pe.fyj.fyj_erp_api.dto.auth;

public record RegisterResponse(
    String message,
    String username,
    String tipo 
) {}