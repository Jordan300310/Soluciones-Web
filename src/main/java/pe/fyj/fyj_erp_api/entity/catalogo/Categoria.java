package pe.fyj.fyj_erp_api.entity.catalogo;

import jakarta.persistence.*;

@Entity
@Table(
  name = "categoria",
  uniqueConstraints = @UniqueConstraint(name = "uk_categoria_nom", columnNames = "nom_categoria")
)
public class Categoria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_categoria")
  private Long idCategoria;

  @Column(name = "nom_categoria", nullable = false, length = 150)
  private String nomCategoria;

  @Column(name = "estado", nullable = false)
  private Integer estado = 1; // 1=activo, 0=inactivo

  public Long getIdCategoria() { return idCategoria; }
  public void setIdCategoria(Long idCategoria) { this.idCategoria = idCategoria; }
  public String getNomCategoria() { return nomCategoria; }
  public void setNomCategoria(String nomCategoria) { this.nomCategoria = nomCategoria; }
  public Integer getEstado() { return estado; }
  public void setEstado(Integer estado) { this.estado = estado; }
}