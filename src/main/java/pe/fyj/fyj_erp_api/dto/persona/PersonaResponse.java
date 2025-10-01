package pe.fyj.fyj_erp_api.dto.persona;

import java.time.LocalDate;
import pe.fyj.fyj_erp_api.entity.persona.Persona;

public record PersonaResponse(
    Long idPersona,
    String nomPersona,
    String apatPersona,
    String amatPersona,
    String dniPersona,
    String celPersona,
    String emPersona,
    LocalDate fePersona
) {
  public static PersonaResponse from(Persona p) {
    return new PersonaResponse(
        p.getIdPersona(),
        p.getNomPersona(),
        p.getApatPersona(),
        p.getAmatPersona(),
        p.getDniPersona(),
        p.getCelPersona(),
        p.getEmPersona(),
        p.getFePersona()
    );
  }
}
