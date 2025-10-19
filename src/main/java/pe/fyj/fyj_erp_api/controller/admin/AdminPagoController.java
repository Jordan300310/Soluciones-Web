package pe.fyj.fyj_erp_api.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.fyj.fyj_erp_api.dto.payment.ChangeEstadoPagoRequest;
import pe.fyj.fyj_erp_api.dto.payment.CreatePaymentRequest;
import pe.fyj.fyj_erp_api.models.payment.Pago;
import pe.fyj.fyj_erp_api.service.admin.AdminPagoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin/pagos")
public class AdminPagoController {
  private final AdminPagoService service;
  public AdminPagoController(AdminPagoService service) { this.service = service; }

  @GetMapping
  public List<Pago> list() { return service.list(); }

  @GetMapping("/{id}")
  public ResponseEntity<Pago> get(@PathVariable Long id) {
    Pago p = service.get(id);
    return (p == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(p);
  }

  @PostMapping
  public ResponseEntity<Pago> create(@RequestBody CreatePaymentRequest body) {
    Pago p = service.create(body);
    return ResponseEntity.created(URI.create("/admin/pagos/" + p.getId())).body(p);
  }

  @PatchMapping("/{id}/estado")
  public ResponseEntity<Void> changeEstado(@PathVariable Long id, @RequestBody ChangeEstadoPagoRequest body) {
    boolean ok = service.changeEstado(id, body);
    return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
}