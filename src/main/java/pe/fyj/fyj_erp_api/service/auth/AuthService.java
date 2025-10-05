package pe.fyj.fyj_erp_api.service.auth;

import jakarta.servlet.http.HttpSession;
import pe.fyj.fyj_erp_api.dto.auth.LoginRequest;
import pe.fyj.fyj_erp_api.dto.auth.RegisterRequest;
import pe.fyj.fyj_erp_api.dto.auth.RegisterResponse;

public interface AuthService {
  void login(LoginRequest request, HttpSession session);
  void logout(HttpSession session);
  RegisterResponse register(RegisterRequest request);
}