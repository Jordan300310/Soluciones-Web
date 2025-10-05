package pe.fyj.fyj_erp_api.models.auth;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity @Table(name = "persona")
@Getter @Setter @NoArgsConstructor
public class Persona {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_persona")
  private Long id;

  @Column(name = "nom_persona", nullable = false, length = 150)
  private String nombres;

  @Column(name = "apat_persona")
  private String apat;

  @Column(name = "amat_persona")
  private String amat;

  @Column(name = "dni_persona", unique = true, length = 15)
  private String dni;

  @Column(name = "cel_persona", length = 20)
  private String celular;

  @Column(name = "email_persona", length = 150)
  private String email;

  @Column(name = "feN_persona")
  private LocalDate fechaNacimiento;
}