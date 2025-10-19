package pe.fyj.fyj_erp_api.controller.admin;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.fyj.fyj_erp_api.dto.purchase.ChangeEstadoCompraRequest;
import pe.fyj.fyj_erp_api.dto.purchase.CreatePurchaseRequest;
import pe.fyj.fyj_erp_api.dto.purchase.PurchaseResponse;
import pe.fyj.fyj_erp_api.models.auth.SessionUser;
import pe.fyj.fyj_erp_api.security.AuthGuard;
import pe.fyj.fyj_erp_api.service.admin.AdminCompraService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin/compras")
public class AdminCompraController {

  private final AdminCompraService service;

  public AdminCompraController(AdminCompraService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<PurchaseResponse> create(@RequestBody CreatePurchaseRequest body, HttpSession session) {
    SessionUser su = AuthGuard.requireEmpleado(session);
    var resp = service.create(su.getIdPersona(), body);
    return ResponseEntity.created(URI.create("/admin/compras/" + resp.id_compra())).body(resp);
  }

  @GetMapping
  public List<?> list() {
    return service.list();
  }

  @GetMapping("/{id}")
  public ResponseEntity<PurchaseResponse> get(@PathVariable Long id) {
    var resp = service.get(id);
    return ResponseEntity.ok(resp);
  }

  @PatchMapping("/{id}/estado")
  public ResponseEntity<Void> changeEstado(@PathVariable Long id, @RequestBody ChangeEstadoCompraRequest body) {
    boolean ok = service.changeEstado(id, body);
    return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
}