package pe.fyj.fyj_erp_api.models.auth;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "usuario")
@Getter @Setter @NoArgsConstructor
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_usuario")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "id_persona")
  private Persona persona;

  @Column(name = "tipo", nullable = false, length = 20)
  private String tipo; 

  @Column(name = "username", nullable = false, unique = true, length = 50)
  private String username;

  @Column(name = "password", nullable = false, length = 255)
  private String password;
}