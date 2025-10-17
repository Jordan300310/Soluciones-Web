package pe.fyj.fyj_erp_api.controller.auth;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.fyj.fyj_erp_api.dto.auth.LoginRequest;
import pe.fyj.fyj_erp_api.dto.auth.LoginResponse;
import pe.fyj.fyj_erp_api.dto.auth.RegisterRequest;
import pe.fyj.fyj_erp_api.dto.auth.RegisterResponse;
import pe.fyj.fyj_erp_api.models.auth.SessionUser;
import pe.fyj.fyj_erp_api.service.auth.AuthService;
@RestController
@RequestMapping
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/auth/register")
  public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
    var resp = authService.register(request);
    return ResponseEntity.status(201).body(resp);
    
  }

  @PostMapping("/auth/login")
  public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request, HttpSession session) {
    SessionUser su = authService.login(request);
    session.setAttribute(AuthService.SESSION_KEY, su);

    String tipo = su.getTipo();
    String message = ("cliente".equalsIgnoreCase(tipo) ? "Login exitoso: cliente"
                     : "empleado".equalsIgnoreCase(tipo) ? "Login exitoso: empleado"
                     : "Login exitoso");

    return ResponseEntity.ok(new LoginResponse(message, tipo));
  }


  @PostMapping("/auth/logout")
  public ResponseEntity<Void> logout(HttpSession session) {
    session.invalidate();
    return ResponseEntity.noContent().build();
  }
}
