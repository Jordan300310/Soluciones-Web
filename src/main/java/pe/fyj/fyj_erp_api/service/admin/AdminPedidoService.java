package pe.fyj.fyj_erp_api.service.admin;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.fyj.fyj_erp_api.dto.order.ChangeEstadoPedidoRequest;
import pe.fyj.fyj_erp_api.models.order.EstadoPedido;
import pe.fyj.fyj_erp_api.models.order.Pedido;
import pe.fyj.fyj_erp_api.repository.order.DetallePedidoRepository;
import pe.fyj.fyj_erp_api.repository.order.EstadoPedidoRepository;
import pe.fyj.fyj_erp_api.repository.order.PedidoRepository;

import java.util.List;

@Service
public class AdminPedidoService {
  private final PedidoRepository pedidoRepo;
  private final DetallePedidoRepository detalleRepo;
  private final EstadoPedidoRepository estadoRepo;

  public AdminPedidoService(PedidoRepository pedidoRepo,
                            DetallePedidoRepository detalleRepo,
                            EstadoPedidoRepository estadoRepo) {
    this.pedidoRepo = pedidoRepo;
    this.detalleRepo = detalleRepo;
    this.estadoRepo = estadoRepo;
  }

  @Transactional(readOnly = true)
  public List<Pedido> list() { return pedidoRepo.findAll(); }

  @Transactional(readOnly = true)
  public Pedido get(Long id) { return pedidoRepo.findById(id).orElse(null); }

  @Transactional
  public boolean changeEstado(Long idPedido, ChangeEstadoPedidoRequest req) {
    Pedido p = pedidoRepo.findById(idPedido).orElse(null);
    if (p == null) return false;
    EstadoPedido e = estadoRepo.findByNombreEstadoIgnoreCase(req.estado()).orElse(null);
    if (e == null) return false;
    p.setIdEstadoPedido(e.getId());
    pedidoRepo.save(p);
    return true;
  }
}