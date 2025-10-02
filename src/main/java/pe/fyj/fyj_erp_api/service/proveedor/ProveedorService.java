package pe.fyj.fyj_erp_api.service.proveedor;

import java.util.List;
import pe.fyj.fyj_erp_api.dto.proveedor.*;

public interface ProveedorService {
  List<ProveedorResponse> listar();
  ProveedorResponse obtener(Long id);
  ProveedorResponse crear(ProveedorRequest req);
  ProveedorResponse actualizar(Long id, ProveedorRequest req);
  void eliminar(Long id);

 
  void activar(Long id);
  void desactivar(Long id);
}