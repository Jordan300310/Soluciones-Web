package pe.fyj.fyj_erp_api.controller.proveedor;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import pe.fyj.fyj_erp_api.dto.proveedor.*;
import pe.fyj.fyj_erp_api.service.proveedor.ProveedorService;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

  private final ProveedorService service;
  public ProveedorController(ProveedorService service) { this.service = service; }

  @GetMapping
  public List<ProveedorResponse> listar() { return service.listar(); }

  @GetMapping("/{id}")
  public ProveedorResponse obtener(@PathVariable Long id) { return service.obtener(id); }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ProveedorResponse crear(@Valid @RequestBody ProveedorRequest req) {
    return service.crear(req);
  }

  @PutMapping("/{id}")
  public ProveedorResponse actualizar(@PathVariable Long id, @Valid @RequestBody ProveedorRequest req) {
    return service.actualizar(id, req);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void eliminar(@PathVariable Long id) { service.eliminar(id); }

  // Acciones no-CRUD para estado
  @PutMapping("/{id}/activar")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void activar(@PathVariable Long id) { service.activar(id); }

  @PutMapping("/{id}/desactivar")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void desactivar(@PathVariable Long id) { service.desactivar(id); }
}