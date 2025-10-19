package pe.fyj.fyj_erp_api.models.order;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity 
@Table(name = "detalle_pedido")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class DetallePedido {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_detalle")
  private Long id;

  @Column(name = "id_pedido", nullable = false)
  private Long idPedido;

  @Column(name = "id_producto", nullable = false)
  private Long idProducto;

  @Column(name = "cantidad", nullable = false)
  private Integer cantidad;

  @Column(name = "subtotal", precision = 14, scale = 2, nullable = false)
  private BigDecimal subtotal;
}