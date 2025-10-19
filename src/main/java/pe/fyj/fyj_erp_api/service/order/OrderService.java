package pe.fyj.fyj_erp_api.service.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.fyj.fyj_erp_api.dto.order.CreateOrderRequest;
import pe.fyj.fyj_erp_api.dto.order.OrderResponse;
import pe.fyj.fyj_erp_api.models.auth.Cliente;
import pe.fyj.fyj_erp_api.models.catalog.Producto;
import pe.fyj.fyj_erp_api.models.order.DetallePedido;
import pe.fyj.fyj_erp_api.models.order.EstadoPedido;
import pe.fyj.fyj_erp_api.models.order.Pedido;
import pe.fyj.fyj_erp_api.repository.auth.ClienteRepository;
import pe.fyj.fyj_erp_api.repository.catalog.ProductoRepository;
import pe.fyj.fyj_erp_api.repository.order.DetallePedidoRepository;
import pe.fyj.fyj_erp_api.repository.order.EstadoPedidoRepository;
import pe.fyj.fyj_erp_api.repository.order.PedidoRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
  private final PedidoRepository pedidoRepo;
  private final DetallePedidoRepository detalleRepo;
  private final EstadoPedidoRepository estadoRepo;
  private final ProductoRepository productoRepo;
  private final ClienteRepository clienteRepo;

  public OrderService(PedidoRepository pedidoRepo, DetallePedidoRepository detalleRepo,
                      EstadoPedidoRepository estadoRepo, ProductoRepository productoRepo,
                      ClienteRepository clienteRepo) {
    this.pedidoRepo = pedidoRepo;
    this.detalleRepo = detalleRepo;
    this.estadoRepo = estadoRepo;
    this.productoRepo = productoRepo;
    this.clienteRepo = clienteRepo;
  }

  @Transactional
  public OrderResponse create(Long idPersonaCliente, CreateOrderRequest req) {
    Cliente c = clienteRepo.findByPersonaId(idPersonaCliente).orElseThrow();
    EstadoPedido estadoInit = estadoRepo.findByNombreEstadoIgnoreCase("EN PROCESO").orElseThrow();

    BigDecimal total = BigDecimal.ZERO;
    Pedido ped = Pedido.builder()
        .idCliente(c.getId())
        .fechaPedido(LocalDateTime.now())
        .idEstadoPedido(estadoInit.getId())
        .total(total)
        .build();
    pedidoRepo.save(ped);

    for (var it : req.items()) {
      Producto prod = productoRepo.findById(it.id_producto()).orElseThrow();
      BigDecimal subtotal = prod.getPrecio().multiply(BigDecimal.valueOf(it.cantidad()));
      total = total.add(subtotal);
      detalleRepo.save(DetallePedido.builder()
          .idPedido(ped.getId())
          .idProducto(prod.getId())
          .cantidad(it.cantidad())
          .subtotal(subtotal)
          .build());
    }

    ped.setTotal(total);
    pedidoRepo.save(ped);

    return getResponse(ped.getId());
  }

  @Transactional(readOnly = true)
  public OrderResponse getResponse(Long idPedido) {
    Pedido p = pedidoRepo.findById(idPedido).orElseThrow();
    String estado = estadoRepo.findById(p.getIdEstadoPedido()).map(EstadoPedido::getNombreEstado).orElse(null);

    var detalles = detalleRepo.findByIdPedido(idPedido).stream().map(d -> {
      var prod = productoRepo.findById(d.getIdProducto()).orElse(null);
      return new OrderResponse.Detalle(
          d.getIdProducto(),
          prod != null ? prod.getNombre() : null,
          prod != null ? prod.getPrecio() : null,
          d.getCantidad(),
          d.getSubtotal()
      );
    }).toList();
    return new OrderResponse(p.getId(), p.getFechaPedido(), estado, p.getTotal(), detalles);
  }
}