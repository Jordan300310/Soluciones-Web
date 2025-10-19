package pe.fyj.fyj_erp_api.controller.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.fyj.fyj_erp_api.dto.payment.CreatePaymentRequest;
import pe.fyj.fyj_erp_api.dto.payment.PaymentResponse;
import pe.fyj.fyj_erp_api.service.payment.PaymentService;

@RestController
@RequestMapping
public class PaymentController {
  private final PaymentService service;
  public PaymentController(PaymentService service) { this.service = service; }

  @PostMapping("/payments")
  public ResponseEntity<PaymentResponse> create(@RequestBody CreatePaymentRequest body) {
    var resp = service.create(body);
    return ResponseEntity.status(201).body(resp);
  }
}