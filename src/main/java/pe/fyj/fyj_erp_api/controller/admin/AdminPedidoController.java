package pe.fyj.fyj_erp_api.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.fyj.fyj_erp_api.dto.order.ChangeEstadoPedidoRequest;
import pe.fyj.fyj_erp_api.models.order.Pedido;
import pe.fyj.fyj_erp_api.service.admin.AdminPedidoService;

import java.util.List;

@RestController
@RequestMapping("/admin/pedidos")
public class AdminPedidoController {
  private final AdminPedidoService service;
  public AdminPedidoController(AdminPedidoService service) { this.service = service; }

  @GetMapping
  public List<Pedido> list() { return service.list(); }

  @GetMapping("/{id}")
  public ResponseEntity<Pedido> get(@PathVariable Long id) {
    Pedido p = service.get(id);
    return (p == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(p);
  }

  @PatchMapping("/{id}/estado")
  public ResponseEntity<Void> changeEstado(@PathVariable Long id, @RequestBody ChangeEstadoPedidoRequest body) {
    boolean ok = service.changeEstado(id, body);
    return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
}