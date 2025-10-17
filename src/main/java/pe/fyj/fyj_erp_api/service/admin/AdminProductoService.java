package pe.fyj.fyj_erp_api.service.admin;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.fyj.fyj_erp_api.models.catalog.Producto;
import pe.fyj.fyj_erp_api.repository.catalog.ProductoRepository;

import java.util.List;

@Service
public class AdminProductoService {
  private final ProductoRepository repo;
  public AdminProductoService(ProductoRepository repo) { this.repo = repo; }

  @Transactional 
  public Producto create(Producto p) {
     return repo.save(p); }

  @Transactional(readOnly = true) 
  public List<Producto> list() { 
    return repo.findAll(); }

  @Transactional(readOnly = true) 
  public Producto get(Long id) {
     return repo.findById(id).orElse(null); }

  @Transactional 
  public Producto update(Long id, Producto in) {
    Producto db = repo.findById(id).orElse(null);
    if (db == null) return null;
    if (in.getNombre() != null) db.setNombre(in.getNombre());
    if (in.getDescripcion() != null) db.setDescripcion(in.getDescripcion());
    if (in.getPrecio() != null) db.setPrecio(in.getPrecio());
    if (in.getStock() != null) db.setStock(in.getStock());
    if (in.getIdMarca() != null) db.setIdMarca(in.getIdMarca());
    if (in.getIdCategoria() != null) db.setIdCategoria(in.getIdCategoria());
    if (in.getIdProveedor() != null) db.setIdProveedor(in.getIdProveedor());
    if (in.getEstado() != null) db.setEstado(in.getEstado());
    return repo.save(db);
  }

  @Transactional 
  public void delete(Long id) {
     repo.deleteById(id); }
}