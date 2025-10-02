package pe.fyj.fyj_erp_api.controller.catalogo;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import pe.fyj.fyj_erp_api.dto.catalogo.*;
import pe.fyj.fyj_erp_api.service.catalogo.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

  private final CategoriaService service;
  public CategoriaController(CategoriaService service) { this.service = service; }

  @GetMapping public List<CategoriaResponse> listar() { return service.listar(); }
  @GetMapping("/{id}") public CategoriaResponse obtener(@PathVariable Long id) { return service.obtener(id); }

  @PostMapping @ResponseStatus(HttpStatus.CREATED)
  public CategoriaResponse crear(@Valid @RequestBody CategoriaRequest req) { return service.crear(req); }

  @PutMapping("/{id}")
  public CategoriaResponse actualizar(@PathVariable Long id, @Valid @RequestBody CategoriaRequest req) {
    return service.actualizar(id, req);
  }

  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
  public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}