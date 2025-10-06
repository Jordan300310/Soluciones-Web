package pe.fyj.fyj_erp_api.models.order;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

import pe.fyj.fyj_erp_api.models.catalog.Producto;

@Entity
@Table(name = "detalle_pedido")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DetallePedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_detalle")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_pedido", nullable = false)
  private Pedido pedido;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_producto", nullable = false)
  private Producto producto;

  @Column(name = "cantidad", nullable = false)
  private Integer cantidad;

  @Column(name = "subtotal", nullable = false, precision = 14, scale = 2)
  private BigDecimal subtotal;
}