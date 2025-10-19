package pe.fyj.fyj_erp_api.models.purchase;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "estado_compra")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class EstadoCompra {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_estado_compra")
  private Long id;

  @Column(name = "nombre_estado", nullable = false, unique = true, length = 50)
  private String nombreEstado;
}