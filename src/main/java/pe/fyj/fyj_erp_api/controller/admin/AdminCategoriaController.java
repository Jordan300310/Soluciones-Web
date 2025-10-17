package pe.fyj.fyj_erp_api.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.fyj.fyj_erp_api.models.catalog.Categoria;
import pe.fyj.fyj_erp_api.service.admin.AdminCategoriaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin/categorias")
public class AdminCategoriaController {
private final AdminCategoriaService service;
public AdminCategoriaController(AdminCategoriaService service) { this.service = service; }

@PostMapping
public ResponseEntity<Categoria> create(@RequestBody Categoria body) {
    Categoria c = service.create(body);
    return ResponseEntity.created(URI.create("/admin/categorias/" + c.getId())).body(c);
}

@GetMapping
public List<Categoria> list() { return service.list(); }

@GetMapping("/{id}")
public ResponseEntity<Categoria> get(@PathVariable Long id) {
    Categoria c = service.get(id);
    return (c == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(c);
}

@PatchMapping("/{id}")
public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody Categoria body) {
    Categoria c = service.update(id, body);
    return (c == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(c);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
}
}