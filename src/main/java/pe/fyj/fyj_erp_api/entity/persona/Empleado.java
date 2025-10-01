package pe.fyj.fyj_erp_api.entity.persona;

import jakarta.persistence.*;
import org.springframework.data.domain.Persistable;

@Entity
@Table(name = "empleado")
public class Empleado implements Persistable<Long> {

  @Id
  @Column(name = "id_persona_emp")
  private Long idPersonaEmp;              

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @MapsId                                  
  @JoinColumn(name = "id_persona_emp")
  private Persona persona;

  @Column(name = "est_empleado", nullable = false)
  private Integer estEmpleado = 1;

  // ---- Persistable: indica si es nuevo para forzar PERSIST en vez de MERGE
  @Transient
  private boolean isNew = true;

  @PostLoad @PostPersist
  private void markNotNew() { this.isNew = false; }

  @Override
  public Long getId() { return idPersonaEmp; }

  @Override
  public boolean isNew() { return isNew; }

  // getters/setters
  public Long getIdPersonaEmp() { return idPersonaEmp; }
  public void setIdPersonaEmp(Long idPersonaEmp) { this.idPersonaEmp = idPersonaEmp; }
  public Persona getPersona() { return persona; }
  public void setPersona(Persona persona) { this.persona = persona; }
  public Integer getEstEmpleado() { return estEmpleado; }
  public void setEstEmpleado(Integer estEmpleado) { this.estEmpleado = estEmpleado; }
}
