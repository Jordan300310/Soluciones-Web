package pe.fyj.fyj_erp_api.models.order;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity 
@Table(name = "pedido")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Pedido {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_pedido")
  private Long id;

  @Column(name = "id_cliente", nullable = false)
  private Long idCliente;

  @Column(name = "fecha_pedido")
  private LocalDateTime fechaPedido;

  @Column(name = "id_estado_pedido")
  private Long idEstadoPedido;

  @Column(name = "total", precision = 14, scale = 2, nullable = false)
  private BigDecimal total;
}