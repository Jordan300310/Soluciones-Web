package pe.fyj.fyj_erp_api.dto.catalogo;

import jakarta.validation.constraints.NotBlank;
import pe.fyj.fyj_erp_api.entity.catalogo.Categoria;

public record CategoriaRequest(
    @NotBlank String nomCategoria,
    Integer estado 
) {
  public Categoria toEntity() {
    var c = new Categoria();
    c.setNomCategoria(nomCategoria);
    c.setEstado(estado != null ? estado : 1);
    return c;
  }

  public void apply(Categoria c) {
    c.setNomCategoria(nomCategoria);
    c.setEstado(estado != null ? estado : c.getEstado());
  }
}