package pe.fyj.fyj_erp_api.models.ticket;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import pe.fyj.fyj_erp_api.models.auth.Empleado;

@Entity
@Table(name = "atencion_ticket")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AtencionTicket {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_atencion")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_ticket", nullable = false)
  private Ticket ticket;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_empleado", nullable = false)
  private Empleado empleado;

  @Column(name = "fecha_atencion", nullable = false)
  private LocalDateTime fechaAtencion = LocalDateTime.now();

  @Column(name = "observaciones")
  private String observaciones;
}