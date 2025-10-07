package pe.fyj.fyj_erp_api.models.catalog;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "producto")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Producto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_producto")
  private Long id;

  @Column(name = "nombre_producto", nullable = false, length = 150)
  private String nombre;

  @Column(name = "descripcion")
  private String descripcion;

  @Column(name = "precio", nullable = false, precision = 10, scale = 2)
  private BigDecimal precio;

  @Column(name = "stock", nullable = false)
  private Integer stock;

  @Column(name = "id_proveedor")
  private Long idProveedor;

  @Column(name = "estado")
  private Boolean estado = true;

}
