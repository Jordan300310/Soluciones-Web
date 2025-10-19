package pe.fyj.fyj_erp_api.service.admin;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.fyj.fyj_erp_api.dto.purchase.ChangeEstadoCompraRequest;
import pe.fyj.fyj_erp_api.dto.purchase.CreatePurchaseRequest;
import pe.fyj.fyj_erp_api.dto.purchase.PurchaseResponse;
import pe.fyj.fyj_erp_api.models.catalog.Producto;
import pe.fyj.fyj_erp_api.models.purchase.Compra;
import pe.fyj.fyj_erp_api.models.purchase.DetalleCompra;
import pe.fyj.fyj_erp_api.models.purchase.EstadoCompra;
import pe.fyj.fyj_erp_api.repository.purchase.CompraRepository;
import pe.fyj.fyj_erp_api.repository.purchase.DetalleCompraRepository;
import pe.fyj.fyj_erp_api.repository.purchase.EstadoCompraRepository;
import pe.fyj.fyj_erp_api.repository.catalog.ProductoRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminCompraService {

  private final CompraRepository compraRepo;
  private final DetalleCompraRepository detalleRepo;
  private final EstadoCompraRepository estadoRepo;
  private final ProductoRepository productoRepo;

  public AdminCompraService(CompraRepository compraRepo,
                            DetalleCompraRepository detalleRepo,
                            EstadoCompraRepository estadoRepo,
                            ProductoRepository productoRepo) {
    this.compraRepo = compraRepo;
    this.detalleRepo = detalleRepo;
    this.estadoRepo = estadoRepo;
    this.productoRepo = productoRepo;
  }

  // Crear compra (estado inicial: EN REVISIÓN)
  @Transactional
  public PurchaseResponse create(Long idEmpleado, CreatePurchaseRequest req) {
    var estadoIni = estadoRepo.findByNombreEstadoIgnoreCase("EN REVISIÓN").orElseThrow();

    BigDecimal total = BigDecimal.ZERO;
    Compra c = Compra.builder()
        .idEmpleado(idEmpleado)
        .idProveedor(req.id_proveedor())
        .fechaCompra(LocalDateTime.now())
        .idEstadoCompra(estadoIni.getId())
        .total(total)
        .build();
    compraRepo.save(c);

    for (var it : req.items()) {
      BigDecimal subtotal = it.precio_unit().multiply(BigDecimal.valueOf(it.cantidad()));
      total = total.add(subtotal);
      detalleRepo.save(DetalleCompra.builder()
          .idCompra(c.getId())
          .idProducto(it.id_producto())
          .cantidad(it.cantidad())
          .subtotal(subtotal)
          .build());
    }

    c.setTotal(total);
    compraRepo.save(c);

    return getResponse(c.getId());
  }

  @Transactional(readOnly = true)
  public List<Compra> list() { return compraRepo.findAll(); }

  @Transactional(readOnly = true)
  public PurchaseResponse get(Long id) {
    return getResponse(id);
  }

  // Cambiar estado. Si pasa a APROBADA, aumenta stock de productos
  @Transactional
  public boolean changeEstado(Long idCompra, ChangeEstadoCompraRequest req) {
    Compra c = compraRepo.findById(idCompra).orElse(null);
    if (c == null) return false;

    EstadoCompra nuevo = estadoRepo.findByNombreEstadoIgnoreCase(req.estado()).orElse(null);
    if (nuevo == null) return false;

    boolean vaAprobada = "APROBADA".equalsIgnoreCase(nuevo.getNombreEstado());
    boolean yaAprobada = estadoRepo.findById(c.getIdEstadoCompra())
        .map(e -> "APROBADA".equalsIgnoreCase(e.getNombreEstado()))
        .orElse(false);

    c.setIdEstadoCompra(nuevo.getId());
    compraRepo.save(c);

    // Solo cuando transiciona a APROBADA y no lo estaba antes
    if (vaAprobada && !yaAprobada) {
      var detalles = detalleRepo.findByIdCompra(idCompra);
      for (var d : detalles) {
        Producto p = productoRepo.findById(d.getIdProducto()).orElse(null);
        if (p != null) {
          int nuevoStock = (p.getStock() == null ? 0 : p.getStock()) + d.getCantidad();
          p.setStock(nuevoStock);
          productoRepo.save(p);
        }
      }
    }
    return true;
  }

  // ===== Helpers =====
  private PurchaseResponse getResponse(Long idCompra) {
    Compra c = compraRepo.findById(idCompra).orElseThrow();
    String estado = estadoRepo.findById(c.getIdEstadoCompra()).map(EstadoCompra::getNombreEstado).orElse(null);
    var dets = detalleRepo.findByIdCompra(idCompra).stream()
        .map(d -> new PurchaseResponse.Detalle(d.getIdProducto(), d.getCantidad(), d.getSubtotal()))
        .toList();
    return new PurchaseResponse(c.getId(), c.getFechaCompra(), estado, c.getTotal(), dets);
  }
}