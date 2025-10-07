package pe.fyj.fyj_erp_api.service.auth;

import pe.fyj.fyj_erp_api.dto.auth.LoginRequest;
import pe.fyj.fyj_erp_api.dto.auth.RegisterRequest;
import pe.fyj.fyj_erp_api.dto.auth.RegisterResponse;
import pe.fyj.fyj_erp_api.models.auth.SessionUser;

public interface AuthService {
  SessionUser login(LoginRequest request);
  RegisterResponse register(RegisterRequest request);
}