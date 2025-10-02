package pe.fyj.fyj_erp_api.dto.proveedor;

import pe.fyj.fyj_erp_api.entity.proveedor.Proveedor;

public record ProveedorResponse(
    Long idProveedor,
    String nomProveedor,
    String rucProveedor,
    String telefono,
    String email,
    Integer estProveedor
) {
  public static ProveedorResponse from(Proveedor e) {
    return new ProveedorResponse(
        e.getIdProveedor(),
        e.getNomProveedor(),
        e.getRucProveedor(),
        e.getTelefono(),
        e.getEmail(),
        e.getEstProveedor()
    );
  }
}