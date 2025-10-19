package pe.fyj.fyj_erp_api.models.ticket;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name = "atencion_ticket")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AtencionTicket {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_atencion")
  private Long id;

  @Column(name = "id_ticket", nullable = false)
  private Long idTicket;

  @Column(name = "id_empleado", nullable = false)
  private Long idEmpleado;

  @Column(name = "fecha_atencion")
  private LocalDateTime fechaAtencion;

  @Column(name = "observaciones")
  private String observaciones;
}