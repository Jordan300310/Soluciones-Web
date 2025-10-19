package pe.fyj.fyj_erp_api.models.purchase;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity @Table(name = "detalle_compra")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class DetalleCompra {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_detalle")
  private Long id;

  @Column(name = "id_compra", nullable = false)
  private Long idCompra;

  @Column(name = "id_producto", nullable = false)
  private Long idProducto;

  @Column(name = "cantidad", nullable = false)
  private Integer cantidad;

  @Column(name = "subtotal", precision = 14, scale = 2, nullable = false)
  private BigDecimal subtotal;
}