package pe.fyj.fyj_erp_api.controller.marca;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import pe.fyj.fyj_erp_api.dto.marca.*;
import pe.fyj.fyj_erp_api.service.marca.MarcaService;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {

  private final MarcaService service;
  public MarcaController(MarcaService service) { this.service = service; }

  @GetMapping
  public List<MarcaResponse> listar() { 
    return service.listar(); 
  }

  @GetMapping("/{idMarca}")
  public MarcaResponse obtener(@PathVariable Long idMarca) { 
    return service.obtener(idMarca); 
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public MarcaResponse crear(@Valid @RequestBody MarcaRequest req) {
    return service.crear(req);
  }

  @PutMapping("/{idMarca}")
  public MarcaResponse actualizar(@PathVariable Long idMarca, @Valid @RequestBody MarcaRequest req) {
    return service.actualizar(idMarca, req);
  }

  @DeleteMapping("/{idMarca}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void eliminar(@PathVariable Long idMarca) { 
    service.eliminar(idMarca); 
  }
}