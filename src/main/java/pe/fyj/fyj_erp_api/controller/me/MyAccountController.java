package pe.fyj.fyj_erp_api.controller.me;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.fyj.fyj_erp_api.dto.me.ChangePasswordRequest;
import pe.fyj.fyj_erp_api.dto.me.ProfileResponse;
import pe.fyj.fyj_erp_api.dto.me.UpdateProfileRequest;
import pe.fyj.fyj_erp_api.dto.ticket.CreateTicketRequest;
import pe.fyj.fyj_erp_api.dto.ticket.TicketResponse;
import pe.fyj.fyj_erp_api.models.auth.SessionUser;
import pe.fyj.fyj_erp_api.models.order.Pedido;
import pe.fyj.fyj_erp_api.models.payment.Pago;
import pe.fyj.fyj_erp_api.models.ticket.Ticket;
import pe.fyj.fyj_erp_api.security.AuthGuard;
import pe.fyj.fyj_erp_api.service.me.MyAccountService;
import pe.fyj.fyj_erp_api.service.ticket.TicketService;
import java.util.List;

@RestController
@RequestMapping("/me")
public class MyAccountController {

  private final MyAccountService service;
  private final TicketService ticketService;

  public MyAccountController(MyAccountService service,TicketService ticketService) {
    this.ticketService = ticketService;
    this.service = service;
  }

  @GetMapping("/profile")
  public ProfileResponse getProfile(HttpSession session) {
    SessionUser su = AuthGuard.requireCliente(session);
    return service.getProfile(su.getIdPersona());
  }

  @PatchMapping("/profile")
  public ProfileResponse updateProfile(@RequestBody UpdateProfileRequest body, HttpSession session) {
    SessionUser su = AuthGuard.requireCliente(session);
    return service.updateProfile(su.getIdPersona(), body);
  }

  @PatchMapping("/password")
  public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordRequest body, HttpSession session) {
    SessionUser su = AuthGuard.requireCliente(session);
    service.changePassword(su.getIdPersona(), body);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/orders")
  public List<Pedido> myOrders(HttpSession session) {
    SessionUser su = AuthGuard.requireCliente(session);
    return service.myOrders(su.getIdPersona());
  }

  @GetMapping("/orders/{id}")
  public ResponseEntity<Pedido> myOrder(@PathVariable Long id, HttpSession session) {
    SessionUser su = AuthGuard.requireCliente(session);
    return ResponseEntity.ok(service.myOrderById(su.getIdPersona(), id));
  }

  @GetMapping("/payments")
  public List<Pago> myPayments(HttpSession session) {
    SessionUser su = AuthGuard.requireCliente(session);
    return service.myPayments(su.getIdPersona());
  }

  @GetMapping("/tickets")
  public List<Ticket> myTickets(HttpSession session) {
    SessionUser su = AuthGuard.requireCliente(session);
    return service.myTickets(su.getIdPersona());
  }
  @PostMapping("/tickets")
    public ResponseEntity<TicketResponse> createTicket(@RequestBody CreateTicketRequest body, HttpSession session) {
    SessionUser su = AuthGuard.requireCliente(session);
    var resp = ticketService.create(su.getIdPersona(), body);
    return ResponseEntity.status(201).body(resp);
    }

  @GetMapping("/tickets/{id}")
  public ResponseEntity<Ticket> myTicket(@PathVariable Long id, HttpSession session) {
    SessionUser su = AuthGuard.requireCliente(session);
    return ResponseEntity.ok(service.myTicketById(su.getIdPersona(), id));
  }
}