package pe.fyj.fyj_erp_api.dto.auth;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import pe.fyj.fyj_erp_api.models.auth.Cliente;
import pe.fyj.fyj_erp_api.models.auth.Persona;
import pe.fyj.fyj_erp_api.models.auth.Usuario;

public record RegisterRequest(
    @NotBlank String nombres,
    String apat,
    String amat,
    @NotBlank String dni,
    String celular,
    String email,
    LocalDate fechaNacimiento,
    @NotBlank String username,
    @NotBlank String password
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

  public Cliente toCliente(Persona p) {
    Cliente c = new Cliente();
    c.setPersona(p);
    c.setEstado(true);
    return c;
  }

  public Usuario toUsuario(Persona p, String hashedPassword) {
    Usuario u = new Usuario();
    u.setPersona(p);
    u.setTipo("cliente");
    u.setUsername(username);
    u.setPassword(hashedPassword);
    return u;
  }
}