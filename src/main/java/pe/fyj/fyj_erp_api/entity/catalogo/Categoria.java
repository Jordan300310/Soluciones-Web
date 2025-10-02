package pe.fyj.fyj_erp_api.entity.catalogo;

import jakarta.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_categoria") private Long idCategoria;

  @Column(name = "nom_categoria", nullable = false, length = 150) private String nomCategoria;

  @Column(name = "des_categoria") private String desCategoria;

  @Column(name = "est_categoria", nullable = false) private Integer estCategoria = 1;

  public Long getIdCategoria() { return idCategoria; }
  public void setIdCategoria(Long idCategoria) { this.idCategoria = idCategoria; }
  public String getNomCategoria() { return nomCategoria; }
  public void setNomCategoria(String nomCategoria) { this.nomCategoria = nomCategoria; }
  public String getDesCategoria() { return desCategoria; }
  public void setDesCategoria(String desCategoria) { this.desCategoria = desCategoria; }
  public Integer getEstCategoria() { return estCategoria; }
  public void setEstCategoria(Integer estCategoria) { this.estCategoria = estCategoria; }
}
