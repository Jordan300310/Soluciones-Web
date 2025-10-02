package pe.fyj.fyj_erp_api.dto.marca;

import jakarta.validation.constraints.NotBlank;
import pe.fyj.fyj_erp_api.entity.catalogo.Marca;
import static pe.fyj.fyj_erp_api.shared.Estados.normalizeEstado;

public record MarcaRequest(
    @NotBlank String nomMarca,
    String desMarca,
    Integer estMarca 
) {
  public Marca toEntity() {
    var m = new Marca();
    m.setNomMarca(nomMarca);
    m.setDesMarca(desMarca);
    m.setEstMarca(normalizeEstado(estMarca, null));
    return m;
  }

  public void apply(Marca m) {
    m.setNomMarca(nomMarca);
    m.setDesMarca(desMarca);
    m.setEstMarca(normalizeEstado(estMarca, m.getEstMarca()));
  }
}