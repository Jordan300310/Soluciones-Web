package pe.fyj.fyj_erp_api.models.shipping;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name = "envio")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Envio {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_envio")
  private Long id;

  @Column(name = "id_pedido", nullable = false)
  private Long idPedido;

  @Column(name = "direccion_entrega", length = 255)
  private String direccionEntrega;

  @Column(name = "fecha_envio")
  private LocalDateTime fechaEnvio;

  @Column(name = "id_estado_envio")
  private Long idEstadoEnvio;
}