package pe.fyj.fyj_erp_api.entity.persona;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "persona")
public class Persona {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_persona") private Long idPersona;

  @Column(name = "nom_persona", nullable = false, length = 150) private String nomPersona;
  @Column(name = "apat_persona", length = 100) private String apatPersona;
  @Column(name = "amat_persona", length = 100) private String amatPersona;
  @Column(name = "dni_persona", length = 15, unique = true) private String dniPersona;
  @Column(name = "cel_persona", length = 20) private String celPersona;
  @Column(name = "em_persona", length = 150) private String emPersona;
  @Column(name = "fe_persona") private LocalDate fePersona;

  public Long getIdPersona() { return idPersona; }
  public void setIdPersona(Long idPersona) { this.idPersona = idPersona; }
  public String getNomPersona() { return nomPersona; }
  public void setNomPersona(String nomPersona) { this.nomPersona = nomPersona; }
  public String getApatPersona() { return apatPersona; }
  public void setApatPersona(String apatPersona) { this.apatPersona = apatPersona; }
  public String getAmatPersona() { return amatPersona; }
  public void setAmatPersona(String amatPersona) { this.amatPersona = amatPersona; }
  public String getDniPersona() { return dniPersona; }
  public void setDniPersona(String dniPersona) { this.dniPersona = dniPersona; }
  public String getCelPersona() { return celPersona; }
  public void setCelPersona(String celPersona) { this.celPersona = celPersona; }
  public String getEmPersona() { return emPersona; }
  public void setEmPersona(String emPersona) { this.emPersona = emPersona; }
  public LocalDate getFePersona() { return fePersona; }
  public void setFePersona(LocalDate fePersona) { this.fePersona = fePersona; }
}
