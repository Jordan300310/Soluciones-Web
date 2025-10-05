package pe.fyj.fyj_erp_api.models.auth;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "cliente")
@Getter @Setter @NoArgsConstructor
public class Cliente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_cliente")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "id_persona")
  private Persona persona;

  @Column(name = "estado", nullable = false)
  private Boolean estado = true;
}