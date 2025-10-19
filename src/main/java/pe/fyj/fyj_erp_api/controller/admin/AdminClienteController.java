package pe.fyj.fyj_erp_api.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.fyj.fyj_erp_api.dto.admin.UpdateClienteRequest;
import pe.fyj.fyj_erp_api.models.auth.Cliente;
import pe.fyj.fyj_erp_api.service.admin.AdminClienteService;

import java.util.List;

@RestController
@RequestMapping("/admin/clientes")
public class AdminClienteController {

  private final AdminClienteService service;

  public AdminClienteController(AdminClienteService service) {
    this.service = service;
  }

  @GetMapping
  public List<Cliente> list() {
    return service.list();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Cliente> get(@PathVariable Long id) {
    Cliente c = service.get(id);
    return (c == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(c);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody UpdateClienteRequest body) {
    Cliente c = service.update(id, body);
    return (c == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(c);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}