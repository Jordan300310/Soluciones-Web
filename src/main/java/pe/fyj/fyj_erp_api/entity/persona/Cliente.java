package pe.fyj.fyj_erp_api.entity.persona;

import jakarta.persistence.*;
import org.springframework.data.domain.Persistable;

@Entity
@Table(name = "cliente")
public class Cliente implements Persistable<Long> {

  @Id
  @Column(name = "id_persona_cl")
  private Long idPersonaCl;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @MapsId
  @JoinColumn(name = "id_persona_cl")
  private Persona persona;

  @Column(name = "est_cliente", nullable = false)
  private Integer estCliente = 1;

  @Transient
  private boolean isNew = true;

  @PostLoad @PostPersist
  private void markNotNew() { this.isNew = false; }

  @Override public Long getId() { return idPersonaCl; }
  @Override public boolean isNew() { return isNew; }

  // getters/setters
  public Long getIdPersonaCl() { return idPersonaCl; }
  public void setIdPersonaCl(Long idPersonaCl) { this.idPersonaCl = idPersonaCl; }
  public Persona getPersona() { return persona; }
  public void setPersona(Persona persona) { this.persona = persona; }
  public Integer getEstCliente() { return estCliente; }
  public void setEstCliente(Integer estCliente) { this.estCliente = estCliente; }
}
