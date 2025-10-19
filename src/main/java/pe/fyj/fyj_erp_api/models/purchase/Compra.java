package pe.fyj.fyj_erp_api.models.purchase;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity @Table(name = "compra")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Compra {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_compra")
  private Long id;

  @Column(name = "id_empleado", nullable = false)
  private Long idEmpleado;

  @Column(name = "id_proveedor", nullable = false)
  private Long idProveedor;

  @Column(name = "fecha_compra")
  private LocalDateTime fechaCompra;

  @Column(name = "id_estado_compra")
  private Long idEstadoCompra;

  @Column(name = "total", precision = 14, scale = 2, nullable = false)
  private BigDecimal total;
}