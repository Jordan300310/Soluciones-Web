package pe.fyj.fyj_erp_api.models.order;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import pe.fyj.fyj_erp_api.models.auth.Cliente;

@Entity
@Table(name = "pedido")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Pedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_pedido")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_cliente", nullable = false)
  private Cliente cliente;

  @Column(name = "fecha_pedido", nullable = false)
  private LocalDateTime fechaPedido = LocalDateTime.now();

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_estado_pedido", nullable = false)
  private EstadoPedido estadoPedido;

  @Column(name = "total", nullable = false, precision = 14, scale = 2)
  private BigDecimal total;
}