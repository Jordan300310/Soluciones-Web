// src/main/java/pe/fyj/fyj_erp_api/dto/usuario/UsuarioRequest.java
package pe.fyj.fyj_erp_api.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pe.fyj.fyj_erp_api.entity.usuario.Usuario;

public record UsuarioRequest(
    @NotNull  Long idPersona,
    @NotBlank String username,
    @NotBlank String password
) {
  public Usuario toEntity(String tipo, String passwordHash) {
    var u = new Usuario();
    u.setIdPersona(idPersona);
    u.setUsername(username);
    u.setPasswordHash(passwordHash);
    u.setTipo(tipo);  
    u.setEstado(1);
    return u;
  }
}
