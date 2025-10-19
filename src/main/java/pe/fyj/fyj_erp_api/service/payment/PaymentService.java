package pe.fyj.fyj_erp_api.service.payment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.fyj.fyj_erp_api.dto.payment.CreatePaymentRequest;
import pe.fyj.fyj_erp_api.dto.payment.PaymentResponse;
import pe.fyj.fyj_erp_api.models.payment.EstadoPago;
import pe.fyj.fyj_erp_api.models.payment.Pago;
import pe.fyj.fyj_erp_api.repository.payment.EstadoPagoRepository;
import pe.fyj.fyj_erp_api.repository.payment.PagoRepository;

import java.time.LocalDateTime;

@Service
public class PaymentService {
  private final PagoRepository pagoRepo;
  private final EstadoPagoRepository estadoRepo;

  public PaymentService(PagoRepository pagoRepo, EstadoPagoRepository estadoRepo) {
    this.pagoRepo = pagoRepo;
    this.estadoRepo = estadoRepo;
  }

  @Transactional
  public PaymentResponse create(CreatePaymentRequest req) {
    var estado = estadoRepo.findByNombreEstadoIgnoreCase("PENDIENTE").orElseThrow();
    Pago p = Pago.builder()
        .idPedido(req.id_pedido())
        .monto(req.monto())
        .metodoPago(req.metodo_pago())
        .fechaPago(LocalDateTime.now())
        .idEstadoPago(estado.getId())
        .build();
    pagoRepo.save(p);
    return toResp(p, "PENDIENTE");
  }

  private PaymentResponse toResp(Pago p, String estado) {
    return new PaymentResponse(p.getId(), p.getIdPedido(), p.getMonto(), p.getMetodoPago(), p.getFechaPago(), estado);
  }
}