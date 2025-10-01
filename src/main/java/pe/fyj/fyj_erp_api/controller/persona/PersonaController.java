package pe.fyj.fyj_erp_api.controller.persona;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import pe.fyj.fyj_erp_api.dto.persona.PersonaRequest;
import pe.fyj.fyj_erp_api.dto.persona.PersonaResponse;
import pe.fyj.fyj_erp_api.service.persona.PersonaService;
import pe.fyj.fyj_erp_api.service.persona.impl.PersonaServiceImpl;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

  private final PersonaService service;
  public PersonaController(PersonaService service) { this.service = service; }

  @GetMapping
  public List<PersonaResponse> listar() {
    return service.listar();
  }

  @GetMapping("/{idPersona}")
  public PersonaResponse obtener(@PathVariable Long idPersona) {
    return service.obtener(idPersona);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PersonaResponse crear(@Valid @RequestBody PersonaRequest req) {
    return service.crear(req);
  }

  @PutMapping("/{idPersona}")
  public PersonaResponse actualizar(@PathVariable Long idPersona, @Valid @RequestBody PersonaRequest req) {
    return service.actualizar(idPersona, req);
  }
  @PatchMapping("/{idPersona}")
   public PersonaResponse actualizarParcial(@PathVariable Long idPersona,
                                            @RequestBody PersonaRequest patch) {
    return ((PersonaServiceImpl) service).actualizarParcial(idPersona, patch);
  } 

  @DeleteMapping("/{idPersona}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void eliminar(@PathVariable Long idPersona) {
    service.eliminar(idPersona);
  }

}
