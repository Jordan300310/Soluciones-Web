package pe.fyj.fyj_erp_api.controller.usuario;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import pe.fyj.fyj_erp_api.dto.usuario.*;
import pe.fyj.fyj_erp_api.service.usuario.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

  private final UsuarioService service;
  public UsuarioController(UsuarioService service) { this.service = service; }

  @GetMapping
  public List<UsuarioResponse> listar() { return service.listar(); }

  @PostMapping("/registro")
  @ResponseStatus(HttpStatus.CREATED)
   public UsuarioResponse registroCliente(@Valid @RequestBody UsuarioRequest req) {
    return service.registrarCliente(req);
  }

  @PostMapping("/registro-empleado")
  @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse registroEmpleado(@Valid @RequestBody UsuarioRequest req) {
    return service.registrarEmpleado(req);
  }

  @PostMapping("/login")
  public UsuarioResult login(@Valid @RequestBody LoginRequest req) {
    var u = service.login(req);
    return new UsuarioResult("Login ok", u);
  }
}

