package pe.fyj.fyj_erp_api.controller.admin;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.fyj.fyj_erp_api.dto.ticket.ChangeEstadoTicketRequest;
import pe.fyj.fyj_erp_api.dto.ticket.CreateAtencionRequest;
import pe.fyj.fyj_erp_api.dto.ticket.TicketResponse;
import pe.fyj.fyj_erp_api.models.ticket.Ticket;
import pe.fyj.fyj_erp_api.models.auth.SessionUser;
import pe.fyj.fyj_erp_api.security.AuthGuard;
import pe.fyj.fyj_erp_api.service.admin.AdminTicketService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin/tickets")
public class AdminTicketController {
  private final AdminTicketService service;
  public AdminTicketController(AdminTicketService service) { this.service = service; }

  @GetMapping
  public List<Ticket> list(HttpSession session) {
    AuthGuard.requireEmpleado(session);
    return service.list();
  }

  @GetMapping("/{id}")
  public ResponseEntity<TicketResponse> get(@PathVariable Long id, HttpSession session) {
    AuthGuard.requireEmpleado(session);
    var resp = service.get(id);
    return (resp == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(resp);
  }

  @PatchMapping("/{id}/estado")
  public ResponseEntity<Void> changeEstado(@PathVariable Long id, @RequestBody ChangeEstadoTicketRequest body, HttpSession session) {
    AuthGuard.requireEmpleado(session);
    boolean ok = service.changeEstado(id, body);
    return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }

  @PostMapping("/{id}/atenciones")
  public ResponseEntity<Void> addAtencion(@PathVariable Long id, @RequestBody CreateAtencionRequest body, HttpSession session) {
    SessionUser su = AuthGuard.requireEmpleado(session);
    boolean ok = service.addAtencion(id, su.getIdPersona(), body);
    return ok ? ResponseEntity.created(URI.create("/admin/tickets/" + id)).build()
              : ResponseEntity.notFound().build();
  }
}