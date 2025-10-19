package pe.fyj.fyj_erp_api.models.payment;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "estado_pago")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class EstadoPago {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_estado_pago")
  private Long id;

  @Column(name = "nombre_estado", nullable = false, unique = true, length = 50)
  private String nombreEstado;
}