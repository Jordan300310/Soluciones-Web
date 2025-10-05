package pe.fyj.fyj_erp_api.controller.auth;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.fyj.fyj_erp_api.dto.auth.LoginRequest;
import pe.fyj.fyj_erp_api.dto.auth.LoginResponse;
import pe.fyj.fyj_erp_api.models.auth.SessionUser;
import pe.fyj.fyj_erp_api.service.auth.AuthService;
import pe.fyj.fyj_erp_api.serviceimpl.auth.AuthServiceImpl;

@RestController
@RequestMapping
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

   @PostMapping("/auth/login")
  public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request, HttpSession session) {
    authService.login(request, session);

    SessionUser su = (SessionUser) session.getAttribute(AuthServiceImpl.SESSION_KEY);
    String tipo = su != null ? su.getTipo() : "desconocido";
    String message = "cliente".equalsIgnoreCase(tipo)
        ? "Login exitoso: cliente"
        : ("empleado".equalsIgnoreCase(tipo) ? "Login exitoso: empleado" : "Login exitoso");

    return ResponseEntity.ok(new LoginResponse(message, tipo));
  }

  @PostMapping("/auth/logout")
  public ResponseEntity<Void> logout(HttpSession session) {
    authService.logout(session);
    return ResponseEntity.noContent().build();
  }

}