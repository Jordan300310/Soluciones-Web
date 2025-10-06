package pe.fyj.fyj_erp_api.models.purchase;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import pe.fyj.fyj_erp_api.models.auth.Empleado;

@Entity
@Table(name = "compra")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Compra {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_compra")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_empleado", nullable = false)
  private Empleado empleado;

  // Guardamos solo el ID del proveedor (no mapeamos entidad Proveedor)
  @Column(name = "id_proveedor", nullable = false)
  private Long idProveedor;

  @Column(name = "fecha_compra")
  private LocalDateTime fechaCompra = LocalDateTime.now();

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_estado_compra", nullable = false)
  private EstadoCompra estadoCompra;

  @Column(name = "total", nullable = false, precision = 14, scale = 2)
  private BigDecimal total;
}