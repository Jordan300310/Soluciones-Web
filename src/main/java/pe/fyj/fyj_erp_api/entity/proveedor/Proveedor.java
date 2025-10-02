package pe.fyj.fyj_erp_api.entity.proveedor;

import jakarta.persistence.*;

@Entity
@Table(name = "proveedor")
public class Proveedor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_proveedor")
  private Long idProveedor;

  @Column(name = "nom_proveedor", nullable = false, length = 200)
  private String nomProveedor;

  @Column(name = "ruc_proveedor", nullable = false, unique = true, length = 15)
  private String rucProveedor;

  @Column(name = "telefono", length = 20)
  private String telefono;

  @Column(name = "email", length = 150)
  private String email;

  @Column(name = "est_proveedor", nullable = false)
  private Integer estProveedor = 1; // 1=activo, 0=inactivo

  // getters / setters
  public Long getIdProveedor() { return idProveedor; }
  public void setIdProveedor(Long idProveedor) { this.idProveedor = idProveedor; }

  public String getNomProveedor() { return nomProveedor; }
  public void setNomProveedor(String nomProveedor) { this.nomProveedor = nomProveedor; }

  public String getRucProveedor() { return rucProveedor; }
  public void setRucProveedor(String rucProveedor) { this.rucProveedor = rucProveedor; }

  public String getTelefono() { return telefono; }
  public void setTelefono(String telefono) { this.telefono = telefono; }

  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }

  public Integer getEstProveedor() { return estProveedor; }
  public void setEstProveedor(Integer estProveedor) { this.estProveedor = estProveedor; }
}