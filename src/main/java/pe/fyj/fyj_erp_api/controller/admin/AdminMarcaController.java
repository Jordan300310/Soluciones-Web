package pe.fyj.fyj_erp_api.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.fyj.fyj_erp_api.models.catalog.Marca;
import pe.fyj.fyj_erp_api.service.admin.AdminMarcaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin/marcas")
public class AdminMarcaController {
private final AdminMarcaService service;
public AdminMarcaController(AdminMarcaService service) { this.service = service; }

@PostMapping
public ResponseEntity<Marca> create(@RequestBody Marca body) {
    Marca m = service.create(body);
    return ResponseEntity.created(URI.create("/admin/marcas/" + m.getId())).body(m);
}

@GetMapping
public List<Marca> list() { return service.list(); }

@GetMapping("/{id}")
public ResponseEntity<Marca> get(@PathVariable Long id) {
    Marca m = service.get(id);
    return (m == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(m);
}

@PatchMapping("/{id}")
public ResponseEntity<Marca> update(@PathVariable Long id, @RequestBody Marca body) {
    Marca m = service.update(id, body);
    return (m == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(m);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
}
}