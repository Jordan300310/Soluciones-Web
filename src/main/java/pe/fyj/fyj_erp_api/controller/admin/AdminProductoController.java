package pe.fyj.fyj_erp_api.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.fyj.fyj_erp_api.models.catalog.Producto;
import pe.fyj.fyj_erp_api.service.admin.AdminProductoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin/productos")
public class AdminProductoController {
  private final AdminProductoService service;
  public AdminProductoController(AdminProductoService service) { this.service = service; }

  @PostMapping
  public ResponseEntity<Producto> create(@RequestBody Producto body) {
    Producto p = service.create(body);
    return ResponseEntity.created(URI.create("/admin/productos/" + p.getId())).body(p);
  }

  @GetMapping
  public List<Producto> list() { return service.list(); }

  @GetMapping("/{id}")
  public ResponseEntity<Producto> get(@PathVariable Long id) {
    Producto p = service.get(id);
    return (p == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(p);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Producto> update(@PathVariable Long id, @RequestBody Producto body) {
    Producto p = service.update(id, body);
    return (p == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(p);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}