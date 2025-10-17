package pe.fyj.fyj_erp_api.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.fyj.fyj_erp_api.dto.admin.CreateEmpleadoRequest;
import pe.fyj.fyj_erp_api.dto.admin.UpdateEmpleadoRequest;
import pe.fyj.fyj_erp_api.models.auth.Empleado;
import pe.fyj.fyj_erp_api.service.admin.AdminEmpleadoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin/empleados")
public class AdminEmpleadoController {

  private final AdminEmpleadoService service;

  public AdminEmpleadoController(AdminEmpleadoService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<Empleado> create(@RequestBody CreateEmpleadoRequest body) {
    Empleado e = service.create(body);
    return ResponseEntity.created(URI.create("/admin/empleados/" + e.getId())).body(e);
  }

  @GetMapping
  public List<Empleado> list() { return service.list(); }

  @GetMapping("/{id}")
  public ResponseEntity<Empleado> get(@PathVariable Long id) {
    Empleado e = service.get(id);
    return (e == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(e);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Empleado> update(@PathVariable Long id, @RequestBody UpdateEmpleadoRequest body) {
    Empleado e = service.update(id, body);
    return (e == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(e);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}