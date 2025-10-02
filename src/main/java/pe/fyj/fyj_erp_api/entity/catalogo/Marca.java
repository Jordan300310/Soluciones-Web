package pe.fyj.fyj_erp_api.entity.catalogo;

import jakarta.persistence.*;

@Entity
@Table(name = "marca")
public class Marca {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_marca")
  private Long idMarca;

  @Column(name = "nom_marca", nullable = false, length = 120, unique = true)
  private String nomMarca;

  public Long getIdMarca() { return idMarca; }
  public void setIdMarca(Long idMarca) { this.idMarca = idMarca; }
  public String getNomMarca() { return nomMarca; }
  public void setNomMarca(String nomMarca) { this.nomMarca = nomMarca; }
}