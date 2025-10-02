package pe.fyj.fyj_erp_api.dto.proveedor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import pe.fyj.fyj_erp_api.entity.proveedor.Proveedor;
import static pe.fyj.fyj_erp_api.shared.Estados.normalizeEstado;

public record ProveedorRequest(
    @NotBlank String nomProveedor,
    @NotBlank @Size(max = 15) String rucProveedor,
    String telefono,
    String email,
    Integer estProveedor 
) {
  public Proveedor toEntity() {
    var e = new Proveedor();
    e.setNomProveedor(nomProveedor);
    e.setRucProveedor(rucProveedor);
    e.setTelefono(telefono);
    e.setEmail(email);
    e.setEstProveedor(normalizeEstado(estProveedor, null));
    return e;
  }
  public void apply(Proveedor e) {
    e.setNomProveedor(nomProveedor);
    e.setRucProveedor(rucProveedor);
    e.setTelefono(telefono);
    e.setEmail(email);
    e.setEstProveedor(normalizeEstado(estProveedor, e.getEstProveedor()));
  }
}