package pe.fyj.fyj_erp_api.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.fyj.fyj_erp_api.dto.admin.CreateProveedorRequest;
import pe.fyj.fyj_erp_api.dto.admin.UpdateProveedorRequest;
import pe.fyj.fyj_erp_api.models.catalog.Proveedor;
import pe.fyj.fyj_erp_api.service.admin.AdminProveedorService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin/proveedores")
public class AdminProveedorController {
private final AdminProveedorService service;
public AdminProveedorController(AdminProveedorService service) { this.service = service; }

@PostMapping
  public ResponseEntity<Proveedor> create(@RequestBody CreateProveedorRequest body) {
    Proveedor p = service.create(body);
    return ResponseEntity.created(URI.create("/admin/proveedores/" + p.getId())).body(p);
}

@GetMapping
public List<Proveedor> list() { return service.list(); }

@GetMapping("/{id}")
public ResponseEntity<Proveedor> get(@PathVariable Long id) {
    Proveedor p = service.get(id);
    return (p == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(p);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Proveedor> update(@PathVariable Long id, @RequestBody UpdateProveedorRequest body) {
    Proveedor p = service.update(id, body);
    return (p == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(p);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}