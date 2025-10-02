package pe.fyj.fyj_erp_api.dto.catalogo;

import pe.fyj.fyj_erp_api.entity.catalogo.Categoria;

public record CategoriaResponse(
    Long idCategoria,
    String nomCategoria,
    Integer estado
) {
  public static CategoriaResponse from(Categoria c) {
    return new CategoriaResponse(
        c.getIdCategoria(), 
        c.getNomCategoria(), 
        c.getEstado());
  }
}