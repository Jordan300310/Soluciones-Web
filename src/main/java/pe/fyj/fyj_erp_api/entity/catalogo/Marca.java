package pe.fyj.fyj_erp_api.entity.catalogo;

import jakarta.persistence.*;

@Entity
@Table(name = "marca")
public class Marca {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_marca") 
  private Long idMarca;

  @Column(name = "nom_marca", nullable = false, length = 150) 
  private String nomMarca;

  @Column(name = "des_marca") 
  private String desMarca;

  @Column(name = "est_marca", nullable = false) 
  private Integer estMarca = 1;

  public Long getIdMarca() { return idMarca; }
  public void setIdMarca(Long idMarca) { this.idMarca = idMarca; }
  public String getNomMarca() { return nomMarca; }
  public void setNomMarca(String nomMarca) { this.nomMarca = nomMarca; }
  public String getDesMarca() { return desMarca; }
  public void setDesMarca(String desMarca) { this.desMarca = desMarca; }
  public Integer getEstMarca() { return estMarca; }
  public void setEstMarca(Integer estMarca) { this.estMarca = estMarca; }
}