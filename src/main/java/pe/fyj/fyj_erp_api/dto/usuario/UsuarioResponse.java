
package pe.fyj.fyj_erp_api.dto.usuario;
import pe.fyj.fyj_erp_api.entity.usuario.Usuario;
public record UsuarioResponse(
    Long idUsuario,
    Long idPersona,
    String username,
    String tipo,
    Integer estado
) {
  public static UsuarioResponse from(Usuario u) {
    return new UsuarioResponse(
        u.getIdUsuario(),
        u.getIdPersona(),
        u.getUsername(),
        u.getTipo(),
        u.getEstado()
    );
  }
}