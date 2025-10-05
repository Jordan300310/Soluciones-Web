package pe.fyj.fyj_erp_api.models.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

@Data @AllArgsConstructor
public class SessionUser implements Serializable {
  @Serial private static final long serialVersionUID = 1L;
  private Long idUsuario;
  private Long idPersona;
  private String username;
  private String tipo;    
  private boolean enabled; 
}