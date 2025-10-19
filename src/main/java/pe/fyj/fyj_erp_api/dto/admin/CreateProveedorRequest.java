package pe.fyj.fyj_erp_api.dto.admin;

import java.time.LocalDate;

import pe.fyj.fyj_erp_api.models.auth.Persona;
import pe.fyj.fyj_erp_api.models.catalog.Proveedor;

public record CreateProveedorRequest(
  String nombres,
  String apat,
  String amat,
  String dni,
  String celular,
  String email,
  LocalDate fechaNacimiento,
  String razonSocial,
  String ruc,
  Boolean estado
) {
  public Persona toPersona() {
    Persona p = new Persona();
    p.setNombres(nombres);
    p.setApat(apat);
    p.setAmat(amat);
    p.setDni(dni);
    p.setCelular(celular);
    p.setEmail(email);
    p.setFechaNacimiento(fechaNacimiento);
    return p;
  }

  public Proveedor toProveedor(Persona p) {
    Proveedor pr = new Proveedor();
    pr.setPersona(p);
    pr.setRazonSocial(razonSocial);
    pr.setRuc(ruc);
    pr.setEstado(true);
    return pr;
  }
}