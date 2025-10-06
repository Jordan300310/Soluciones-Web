package pe.fyj.fyj_erp_api.models.purchase;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

import pe.fyj.fyj_erp_api.models.catalog.Producto;

@Entity
@Table(name = "detalle_compra")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DetalleCompra {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_detalle")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_compra", nullable = false)
  private Compra compra;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_producto", nullable = false)
  private Producto producto;

  @Column(name = "cantidad", nullable = false)
  private Integer cantidad;

  @Column(name = "subtotal", nullable = false, precision = 14, scale = 2)
  private BigDecimal subtotal;
}