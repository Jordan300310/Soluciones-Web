package pe.fyj.fyj_erp_api.models.payment;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity @Table(name = "pago")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Pago {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_pago")
  private Long id;

  @Column(name = "id_pedido", nullable = false)
  private Long idPedido;

  @Column(name = "monto", precision = 14, scale = 2, nullable = false)
  private BigDecimal monto;

  @Column(name = "metodo_pago", length = 50)
  private String metodoPago;

  @Column(name = "fecha_pago")
  private LocalDateTime fechaPago;

  @Column(name = "id_estado_pago")
  private Long idEstadoPago;
}