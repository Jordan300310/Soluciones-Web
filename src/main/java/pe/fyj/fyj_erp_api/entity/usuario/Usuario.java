package pe.fyj.fyj_erp_api.entity.usuario;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_usuario")
  private Long idUsuario;

  @Column(name = "id_persona", nullable = false, unique = true)
  private Long idPersona;

  @Column(name = "username", nullable = false, unique = true, length = 120)
  private String username;

  @Column(name = "password_hash", nullable = false)
  private String passwordHash;

  @Column(name = "estado", nullable = false)
  private Integer estado = 1; // 1=activo

  @Column(name = "tipo", nullable = false, length = 10)
  private String tipo; // 'CLI' | 'EMP'

  public Long getIdUsuario() { return idUsuario; }
  public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
  public Long getIdPersona() { return idPersona; }
  public void setIdPersona(Long idPersona) { this.idPersona = idPersona; }
  public String getUsername() { return username; }
  public void setUsername(String username) { this.username = username; }
  public String getPasswordHash() { return passwordHash; }
  public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
  public Integer getEstado() { return estado; }
  public void setEstado(Integer estado) { this.estado = estado; }
  public String getTipo() { return tipo; }
  public void setTipo(String tipo) { this.tipo = tipo; }
}
