package pe.fyj.fyj_erp_api.entity.producto;

import jakarta.persistence.*;
import java.math.BigDecimal;
import pe.fyj.fyj_erp_api.entity.catalogo.Categoria;
import pe.fyj.fyj_erp_api.entity.catalogo.Marca;

@Entity
@Table(name = "producto")
public class Producto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_producto")
  private Long idProducto;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_categoria", nullable = false)
  private Categoria categoria;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_marca", nullable = false)
  private Marca marca;

  @Column(name = "cod_producto", length = 50, unique = true)
  private String codProducto;

  @Column(name = "nom_producto", nullable = false, length = 200)
  private String nomProducto;

  @Column(name = "des_producto")
  private String desProducto;

  @Column(name = "pco_producto", nullable = false, precision = 14, scale = 2)
  private BigDecimal pcoProducto;

  @Column(name = "pve_producto", nullable = false, precision = 14, scale = 2)
  private BigDecimal pveProducto;

  @Column(name = "stk_producto", nullable = false, precision = 14, scale = 2)
  private BigDecimal stkProducto;

  @Column(name = "est_producto", nullable = false)
  private Integer estProducto; 

  public Long getIdProducto() { return idProducto; }
  public void setIdProducto(Long idProducto) { this.idProducto = idProducto; }
  public Categoria getCategoria() { return categoria; }
  public void setCategoria(Categoria categoria) { this.categoria = categoria; }
  public Marca getMarca() { return marca; }
  public void setMarca(Marca marca) { this.marca = marca; }
  public String getCodProducto() { return codProducto; }
  public void setCodProducto(String codProducto) { this.codProducto = codProducto; }
  public String getNomProducto() { return nomProducto; }
  public void setNomProducto(String nomProducto) { this.nomProducto = nomProducto; }
  public String getDesProducto() { return desProducto; }
  public void setDesProducto(String desProducto) { this.desProducto = desProducto; }
  public BigDecimal getPcoProducto() { return pcoProducto; }
  public void setPcoProducto(BigDecimal pcoProducto) { this.pcoProducto = pcoProducto; }
  public BigDecimal getPveProducto() { return pveProducto; }
  public void setPveProducto(BigDecimal pveProducto) { this.pveProducto = pveProducto; }
  public BigDecimal getStkProducto() { return stkProducto; }
  public void setStkProducto(BigDecimal stkProducto) { this.stkProducto = stkProducto; }
  public Integer getEstProducto() { return estProducto; }
  public void setEstProducto(Integer estProducto) { this.estProducto = estProducto; }
}