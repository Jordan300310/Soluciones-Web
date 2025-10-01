package pe.fyj.fyj_erp_api.service.usuario;

import java.util.List;
import pe.fyj.fyj_erp_api.dto.usuario.*;

public interface UsuarioService {
  List<UsuarioResponse> listar();
  UsuarioResponse registrarCliente(UsuarioRequest req);  // siempre 'CLI'
  UsuarioResponse registrarEmpleado(UsuarioRequest req); // siempre 'EMP'
  UsuarioResponse login(LoginRequest req);
}