package pe.fyj.fyj_erp_api.service.persona;

import java.util.List;
import pe.fyj.fyj_erp_api.dto.persona.PersonaRequest;
import pe.fyj.fyj_erp_api.dto.persona.PersonaResponse;

public interface PersonaService {
  List<PersonaResponse> listar();
  PersonaResponse obtener(Long idPersona);
  PersonaResponse crear(PersonaRequest req);
  PersonaResponse actualizar(Long idPersona, PersonaRequest req);
  PersonaResponse actualizarParcial(Long idPersona, PersonaRequest req);
  void eliminar(Long idPersona);
}
