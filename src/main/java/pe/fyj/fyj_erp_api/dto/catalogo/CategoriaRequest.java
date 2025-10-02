package pe.fyj.fyj_erp_api.dto.catalogo;

import jakarta.validation.constraints.NotBlank;
import pe.fyj.fyj_erp_api.entity.catalogo.Categoria;
import static pe.fyj.fyj_erp_api.shared.Estados.normalizeEstado;

public record CategoriaRequest(
    @NotBlank String nomCategoria,
    String desCategoria,
    Integer estCategoria 
) {
  public Categoria toEntity() {
    var c = new Categoria();
    c.setNomCategoria(nomCategoria);
    c.setDesCategoria(desCategoria);
    c.setEstCategoria(normalizeEstado(estCategoria, null));
    return c;
  }

  public void apply(Categoria c) {
    c.setNomCategoria(nomCategoria);
    c.setDesCategoria(desCategoria);
    c.setEstCategoria(normalizeEstado(estCategoria, c.getEstCategoria()));
  }
}