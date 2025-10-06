package pe.fyj.fyj_erp_api.models.ticket;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import pe.fyj.fyj_erp_api.models.auth.Cliente;

@Entity
@Table(name = "ticket")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Ticket {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_ticket")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_cliente", nullable = false)
  private Cliente cliente;

  @Column(name = "asunto", length = 150)
  private String asunto;

  @Column(name = "descripcion")
  private String descripcion;

  @Column(name = "fecha_creacion", nullable = false)
  private LocalDateTime fechaCreacion = LocalDateTime.now();

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_estado_ticket", nullable = false)
  private EstadoTicket estadoTicket;
}