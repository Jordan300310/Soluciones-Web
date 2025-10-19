package pe.fyj.fyj_erp_api.controller.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import pe.fyj.fyj_erp_api.dto.order.CreateOrderRequest;
import pe.fyj.fyj_erp_api.dto.order.OrderResponse;
import pe.fyj.fyj_erp_api.models.auth.SessionUser;
import pe.fyj.fyj_erp_api.security.AuthGuard;
import pe.fyj.fyj_erp_api.service.auth.AuthService;
import pe.fyj.fyj_erp_api.service.order.OrderService;

@RestController
@RequestMapping
public class OrderController {
  private final OrderService service;
  public OrderController(OrderService service) { this.service = service; }

  @PostMapping("/orders")
  public ResponseEntity<OrderResponse> create(@RequestBody CreateOrderRequest body, HttpSession session) {
    SessionUser su = AuthGuard.requireCliente(session);
    var resp = service.create(su.getIdPersona(), body);
    return ResponseEntity.status(201).body(resp);
  }

  @GetMapping("/orders/{id}")
  public ResponseEntity<OrderResponse> get(@PathVariable Long id, HttpSession session) {
    AuthGuard.requireCliente(session);
    return ResponseEntity.ok(service.getResponse(id));
  }
}