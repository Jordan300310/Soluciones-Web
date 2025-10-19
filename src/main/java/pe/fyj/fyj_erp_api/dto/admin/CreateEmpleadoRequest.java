package pe.fyj.fyj_erp_api.dto.admin;

import java.math.BigDecimal;
import java.time.LocalDate;

import pe.fyj.fyj_erp_api.models.auth.Persona;
import pe.fyj.fyj_erp_api.models.auth.Usuario;
import pe.fyj.fyj_erp_api.models.auth.Empleado;

public record CreateEmpleadoRequest(
  String nombres,
  String apat,
  String amat,
  String dni,
  String celular,
  String email,
  LocalDate fechaNacimiento,
  String username,
  String password,
  String cargo,      // "Admin" o "Empleado"
  BigDecimal sueldo,
  Boolean estado
) {
  public Persona toPersona() {
    var p = new Persona();
    p.setNombres(nombres);
    p.setApat(apat);
    p.setAmat(amat);
    p.setDni(dni);
    p.setCelular(celular);
    p.setEmail(email);
    p.setFechaNacimiento(fechaNacimiento);
    return p;
  }
  public Usuario toUsuario(Persona persona, String hashedPassword) {
    var u = new Usuario();
    u.setPersona(persona);
    u.setTipo("empleado");
    u.setUsername(username);
    u.setPassword(hashedPassword);
    return u;
  }
  public Empleado toEmpleado(Persona persona) {
    var e = new Empleado();
    e.setPersona(persona);
    e.setCargo(cargo);
    e.setSueldo(sueldo);
    e.setEstado(true);
    return e;
  }
}