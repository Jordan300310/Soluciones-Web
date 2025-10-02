package pe.fyj.fyj_erp_api.dto.marca;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import pe.fyj.fyj_erp_api.entity.catalogo.Marca;

public record MarcaRequest(
    @NotBlank @Size(max = 120) String nomMarca
) {
  public Marca toEntity() {
    var m = new Marca();
    m.setNomMarca(nomMarca);
    return m;
  }
  public void apply(Marca m) {
    m.setNomMarca(nomMarca);
  }
}