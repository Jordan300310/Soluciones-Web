package pe.fyj.fyj_erp_api.models.ticket;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name = "ticket")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Ticket {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_ticket")
  private Long id;

  @Column(name = "id_cliente", nullable = false)
  private Long idCliente;

  @Column(name = "asunto", length = 150)
  private String asunto;

  @Column(name = "descripcion")
  private String descripcion;

  @Column(name = "fecha_creacion")
  private LocalDateTime fechaCreacion;

  @Column(name = "id_estado_ticket")
  private Long idEstadoTicket;
}