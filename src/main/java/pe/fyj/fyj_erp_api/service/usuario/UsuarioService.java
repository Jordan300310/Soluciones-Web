package pe.fyj.fyj_erp_api.service.usuario;

import java.util.List;
import pe.fyj.fyj_erp_api.dto.usuario.*;

public interface UsuarioService {
  List<UsuarioResponse> listar();
  UsuarioResponse registrarCliente(UsuarioRequest req);  
  UsuarioResponse registrarEmpleado(UsuarioRequest req); 
  UsuarioResponse login(LoginRequest req);
}