package pe.fyj.fyj_erp_api.service.marca;

import java.util.List;
import pe.fyj.fyj_erp_api.dto.marca.*;

public interface MarcaService {
  List<MarcaResponse> listar();
  MarcaResponse obtener(Long idMarca);
  MarcaResponse crear(MarcaRequest req);
  MarcaResponse actualizar(Long idMarca, MarcaRequest req);
  void eliminar(Long idMarca);
}