package pe.fyj.fyj_erp_api.dto.auth;

import pe.fyj.fyj_erp_api.models.auth.SessionUser;
import pe.fyj.fyj_erp_api.models.auth.Usuario;

public record SessionUserDTO(
    Long idUsuario,
    Long idPersona,
    String username,
    String tipo,
    boolean enabled
) {
  public static SessionUserDTO from(Usuario u, boolean enabled) {
    if (u == null || u.getPersona() == null) return null;
    return new SessionUserDTO(
        u.getId(),
        u.getPersona().getId(),
        u.getUsername(),
        u.getTipo(),
        enabled
    );
  }
  public SessionUser toSessionUser() {
    return new SessionUser(
      idUsuario,
      idPersona, 
      username, 
      tipo,
      enabled
    );
  }
}