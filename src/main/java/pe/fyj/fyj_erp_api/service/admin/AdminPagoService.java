package pe.fyj.fyj_erp_api.service.admin;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.fyj.fyj_erp_api.dto.payment.ChangeEstadoPagoRequest;
import pe.fyj.fyj_erp_api.dto.payment.CreatePaymentRequest;
import pe.fyj.fyj_erp_api.dto.payment.PaymentResponse;
import pe.fyj.fyj_erp_api.models.payment.EstadoPago;
import pe.fyj.fyj_erp_api.models.payment.Pago;
import pe.fyj.fyj_erp_api.repository.payment.EstadoPagoRepository;
import pe.fyj.fyj_erp_api.repository.payment.PagoRepository;

import java.util.List;

@Service
public class AdminPagoService {
  private final PagoRepository pagoRepo;
  private final EstadoPagoRepository estadoRepo;

  public AdminPagoService(PagoRepository pagoRepo, EstadoPagoRepository estadoRepo) {
    this.pagoRepo = pagoRepo;
    this.estadoRepo = estadoRepo;
  }

  @Transactional(readOnly = true)
  public List<Pago> list() { return pagoRepo.findAll(); }

  @Transactional(readOnly = true)
  public Pago get(Long id) { return pagoRepo.findById(id).orElse(null); }

  @Transactional
  public Pago create(CreatePaymentRequest req) {
    var estado = estadoRepo.findByNombreEstadoIgnoreCase("PENDIENTE").orElseThrow();
    Pago p = Pago.builder()
        .idPedido(req.id_pedido())
        .monto(req.monto())
        .metodoPago(req.metodo_pago())
        .idEstadoPago(estado.getId())
        .build();
    return pagoRepo.save(p);
  }

  @Transactional
  public boolean changeEstado(Long idPago, ChangeEstadoPagoRequest req) {
    Pago p = pagoRepo.findById(idPago).orElse(null);
    if (p == null) return false;
    EstadoPago e = estadoRepo.findByNombreEstadoIgnoreCase(req.estado()).orElse(null);
    if (e == null) return false;
    p.setIdEstadoPago(e.getId());
    pagoRepo.save(p);
    return true;
  }
}