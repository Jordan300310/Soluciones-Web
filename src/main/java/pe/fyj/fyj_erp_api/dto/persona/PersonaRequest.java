package pe.fyj.fyj_erp_api.dto.persona;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import pe.fyj.fyj_erp_api.entity.persona.Persona;

public record PersonaRequest(
    @NotBlank String nomPersona,
    String apatPersona,
    String amatPersona,
    @Size(max = 15) 
    String dniPersona,
    String celPersona,
    String emPersona,
    LocalDate fePersona
) {
  public Persona toEntity() {
    var p = new Persona();
    p.setNomPersona(nomPersona);
    p.setApatPersona(apatPersona);
    p.setAmatPersona(amatPersona);
    p.setDniPersona(dniPersona);
    p.setCelPersona(celPersona);
    p.setEmPersona(emPersona);
    p.setFePersona(fePersona);
    return p;
  }

  public void apply(Persona p) {
    p.setNomPersona(nomPersona);
    p.setApatPersona(apatPersona);
    p.setAmatPersona(amatPersona);
    p.setDniPersona(dniPersona);
    p.setCelPersona(celPersona);
    p.setEmPersona(emPersona);
    p.setFePersona(fePersona);
  }
    public void applyNonNull(Persona p) {
    if (nomPersona  != null) p.setNomPersona(nomPersona);
    if (apatPersona != null) p.setApatPersona(apatPersona);
    if (amatPersona != null) p.setAmatPersona(amatPersona);
    if (dniPersona  != null) p.setDniPersona(dniPersona);
    if (celPersona  != null) p.setCelPersona(celPersona);
    if (emPersona   != null) p.setEmPersona(emPersona);
    if (fePersona   != null) p.setFePersona(fePersona);
  }
}
