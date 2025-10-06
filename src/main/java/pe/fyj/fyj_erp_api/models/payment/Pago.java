package pe.fyj.fyj_erp_api.models.payment;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import pe.fyj.fyj_erp_api.models.order.Pedido;

@Entity
@Table(name = "pago")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Pago {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_pago")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_pedido", nullable = false)
  private Pedido pedido;

  @Column(name = "monto", nullable = false, precision = 14, scale = 2)
  private BigDecimal monto;

  @Column(name = "metodo_pago", length = 50)
  private String metodoPago;

  @Column(name = "fecha_pago")
  private LocalDateTime fechaPago = LocalDateTime.now();

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_estado_pago", nullable = false)
  private EstadoPago estadoPago;
}