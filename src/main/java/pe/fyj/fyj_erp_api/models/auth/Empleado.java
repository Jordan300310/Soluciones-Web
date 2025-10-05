package pe.fyj.fyj_erp_api.models.auth;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity @Table(name = "empleado")
@Getter @Setter @NoArgsConstructor
public class Empleado {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_empleado")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "id_persona")
  private Persona persona;

  private String cargo;
  private BigDecimal sueldo;

  @Column(name = "estado", nullable = false)
  private Boolean estado = true;
}