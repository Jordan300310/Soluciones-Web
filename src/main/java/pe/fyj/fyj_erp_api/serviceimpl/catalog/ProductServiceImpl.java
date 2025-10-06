package pe.fyj.fyj_erp_api.serviceimpl.catalog;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pe.fyj.fyj_erp_api.dto.catalog.ProductUpsertRequest;
import pe.fyj.fyj_erp_api.enums.ProductListRequest;
import pe.fyj.fyj_erp_api.models.catalog.Producto;
import pe.fyj.fyj_erp_api.repository.catalog.ProductoRepository;
import pe.fyj.fyj_erp_api.service.catalog.ProductService;

import java.util.List;
import java.util.Locale;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductoRepository repo;
  public ProductServiceImpl(ProductoRepository repo) { this.repo = repo; }

  @Override
  @Transactional(readOnly = true)
  public List<Producto> list(ProductListRequest request, String q, Boolean estado) {
    List<Producto> base = switch (request) {
      case PUBLIC, CLIENT -> repo.findByEstadoTrue();
      case ADMIN ->
          (estado == null) ? repo.findAll() : repo.findByEstado(estado);
    };

    if (q == null || q.trim().isEmpty()) return base;

    String term = q.trim().toLowerCase(Locale.ROOT);
    return base.stream()
        .filter(p ->
            (p.getNombre() != null && p.getNombre().toLowerCase(Locale.ROOT).contains(term)) ||
            (p.getDescripcion() != null && p.getDescripcion().toLowerCase(Locale.ROOT).contains(term))
        )
        .toList();
  }

  @Override
  @Transactional(readOnly = true)
  public Producto getOneOrNotFound(Long id) {
    return repo.findById(id)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
  }

  @Override @Transactional
  public Producto create(ProductUpsertRequest req) {
    var entity = req.toNewEntity();
    return repo.save(entity);
  }

  @Override @Transactional
  public Producto update(Long id, ProductUpsertRequest req) {
    var entity = getOneOrNotFound(id);
    req.applyTo(entity);
    return repo.save(entity);
  }

  @Override @Transactional
  public void changeEstado(Long id, boolean estado) {
    var p = getOneOrNotFound(id);
    p.setEstado(estado);
    repo.save(p);
  }

  @Override @Transactional
  public void deleteLogical(Long id) {
    changeEstado(id, false);
  }
}