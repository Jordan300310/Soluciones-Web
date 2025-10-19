package pe.fyj.fyj_erp_api.service.admin;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.fyj.fyj_erp_api.dto.shipping.ChangeEstadoEnvioRequest;
import pe.fyj.fyj_erp_api.dto.shipping.CreateShippingRequest;
import pe.fyj.fyj_erp_api.dto.shipping.ShippingResponse;
import pe.fyj.fyj_erp_api.models.shipping.Envio;
import pe.fyj.fyj_erp_api.models.shipping.EstadoEnvio;
import pe.fyj.fyj_erp_api.repository.shipping.EnvioRepository;
import pe.fyj.fyj_erp_api.repository.shipping.EstadoEnvioRepository;

import java.util.List;

@Service
public class AdminEnvioService {
  private final EnvioRepository envioRepo;
  private final EstadoEnvioRepository estadoRepo;

  public AdminEnvioService(EnvioRepository envioRepo, EstadoEnvioRepository estadoRepo) {
    this.envioRepo = envioRepo;
    this.estadoRepo = estadoRepo;
  }

  @Transactional
  public ShippingResponse create(CreateShippingRequest req) {
    var estadoIni = estadoRepo.findByNombreEstadoIgnoreCase("PREPARANDO").orElseThrow();

    Envio e = Envio.builder()
        .idPedido(req.id_pedido())
        .direccionEntrega(req.direccion_entrega())
        .fechaEnvio(req.fecha_envio())
        .idEstadoEnvio(estadoIni.getId())
        .build();
    envioRepo.save(e);

    return toResp(e, estadoIni.getNombreEstado());
  }

  @Transactional(readOnly = true)
  public List<Envio> list() { return envioRepo.findAll(); }

  @Transactional(readOnly = true)
  public ShippingResponse get(Long id) {
    Envio e = envioRepo.findById(id).orElse(null);
    if (e == null) return null;
    String estado = estadoRepo.findById(e.getIdEstadoEnvio()).map(EstadoEnvio::getNombreEstado).orElse(null);
    return toResp(e, estado);
  }

  @Transactional
  public boolean changeEstado(Long idEnvio, ChangeEstadoEnvioRequest req) {
    Envio e = envioRepo.findById(idEnvio).orElse(null);
    if (e == null) return false;
    EstadoEnvio nuevo = estadoRepo.findByNombreEstadoIgnoreCase(req.estado()).orElse(null);
    if (nuevo == null) return false;
    e.setIdEstadoEnvio(nuevo.getId());
    envioRepo.save(e);
    return true;
  }

  private ShippingResponse toResp(Envio e, String estado) {
    return new ShippingResponse(
        e.getId(), e.getIdPedido(), e.getDireccionEntrega(), e.getFechaEnvio(), estado
    );
  }
}