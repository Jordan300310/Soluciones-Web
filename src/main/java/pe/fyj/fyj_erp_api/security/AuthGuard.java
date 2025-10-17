package pe.fyj.fyj_erp_api.security;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import pe.fyj.fyj_erp_api.models.auth.SessionUser;
import pe.fyj.fyj_erp_api.service.auth.AuthService;

public final class AuthGuard {
  private AuthGuard() {}

  public static SessionUser requireCliente(HttpSession session) {
    SessionUser su = (SessionUser) session.getAttribute(AuthService.SESSION_KEY);
    if (su == null || !su.isEnabled()) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Inicia sesión");
    if (!"cliente".equalsIgnoreCase(su.getTipo())) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Solo clientes");
    return su;
  }

  public static SessionUser requireEmpleado(HttpSession session) {
    SessionUser su = (SessionUser) session.getAttribute(AuthService.SESSION_KEY);
    if (su == null || !su.isEnabled()) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Inicia sesión");
    if (!"empleado".equalsIgnoreCase(su.getTipo())) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Solo empleados");
    return su;
  }
}
