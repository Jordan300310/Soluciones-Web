package pe.fyj.fyj_erp_api.dto.marca;

import pe.fyj.fyj_erp_api.entity.catalogo.Marca;

public record MarcaResponse(
    Long idMarca,
    String nomMarca
) {
  public static MarcaResponse from(Marca m) {
    return new MarcaResponse(
        m.getIdMarca(), 
        m.getNomMarca());
  }
}