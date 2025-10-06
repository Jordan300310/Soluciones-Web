package pe.fyj.fyj_erp_api.models.shipping;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import pe.fyj.fyj_erp_api.models.order.Pedido;

@Entity
@Table(name = "envio")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Envio {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_envio")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_pedido", nullable = false)
  private Pedido pedido;

  @Column(name = "direccion_entrega", length = 255)
  private String direccionEntrega;

  @Column(name = "fecha_envio")
  private LocalDateTime fechaEnvio;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_estado_envio", nullable = false)
  private EstadoEnvio estadoEnvio;
}