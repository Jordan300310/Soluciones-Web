package pe.fyj.fyj_erp_api.models.purchase;

import jakarta.persistence.*;
import lombok.*;
import pe.fyj.fyj_erp_api.models.auth.Persona;

@Entity
@Table(name = "proveedor")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Proveedor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_proveedor")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_persona")
  private Persona persona;

  @Column(name = "razon_social", length = 150)
  private String razonSocial;

  @Column(name = "ruc", length = 15, unique = true)
  private String ruc;

  @Column(name = "estado")
  private Boolean estado; 
}