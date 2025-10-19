package pe.fyj.fyj_erp_api.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.fyj.fyj_erp_api.dto.shipping.ChangeEstadoEnvioRequest;
import pe.fyj.fyj_erp_api.dto.shipping.CreateShippingRequest;
import pe.fyj.fyj_erp_api.dto.shipping.ShippingResponse;
import pe.fyj.fyj_erp_api.models.shipping.Envio;
import pe.fyj.fyj_erp_api.service.admin.AdminEnvioService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin/envios")
public class AdminEnvioController {
  private final AdminEnvioService service;
  public AdminEnvioController(AdminEnvioService service) { this.service = service; }

  @PostMapping
  public ResponseEntity<ShippingResponse> create(@RequestBody CreateShippingRequest body) {
    var resp = service.create(body);
    return ResponseEntity.created(URI.create("/admin/envios/" + resp.id_envio())).body(resp);
  }

  @GetMapping
  public List<Envio> list() { return service.list(); }

  @GetMapping("/{id}")
  public ResponseEntity<ShippingResponse> get(@PathVariable Long id) {
    var resp = service.get(id);
    return (resp == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(resp);
  }

  @PatchMapping("/{id}/estado")
  public ResponseEntity<Void> changeEstado(@PathVariable Long id, @RequestBody ChangeEstadoEnvioRequest body) {
    boolean ok = service.changeEstado(id, body);
    return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
}