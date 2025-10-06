package pe.fyj.fyj_erp_api.models.ticket;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estado_ticket")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class EstadoTicket {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_estado_ticket")
  private Long id;

  @Column(name = "nombre_estado", nullable = false, unique = true, length = 50)
  private String nombreEstado;
}