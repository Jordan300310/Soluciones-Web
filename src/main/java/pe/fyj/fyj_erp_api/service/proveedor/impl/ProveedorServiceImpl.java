// src/main/java/pe/fyj/fyj_erp_api/service/proveedor/impl/ProveedorServiceImpl.java
package pe.fyj.fyj_erp_api.service.proveedor.impl;

import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

import pe.fyj.fyj_erp_api.dto.proveedor.*;
import pe.fyj.fyj_erp_api.entity.proveedor.Proveedor;
import pe.fyj.fyj_erp_api.repository.proveedor.ProveedorRepository;
import pe.fyj.fyj_erp_api.service.proveedor.ProveedorService;

@Service
@Transactional
public class ProveedorServiceImpl implements ProveedorService {

  private final ProveedorRepository repo;

  public ProveedorServiceImpl(ProveedorRepository repo) { this.repo = repo; }

  @Override
  public List<ProveedorResponse> listar() {
    return repo.findAll().stream().map(ProveedorResponse::from).toList();
  }

  @Override
  public ProveedorResponse obtener(Long id) {
    var e = repo.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proveedor no encontrado"));
    return ProveedorResponse.from(e);
  }

  @Override
  public ProveedorResponse crear(ProveedorRequest req) {
    try {
      var e = repo.save(req.toEntity());
      return ProveedorResponse.from(e);
    } catch (DataIntegrityViolationException ex) {
      // choca con UNIQUE(ruc_proveedor)
      throw new ResponseStatusException(HttpStatus.CONFLICT, "RUC de proveedor duplicado", ex);
    }
  }

  @Override
  public ProveedorResponse actualizar(Long id, ProveedorRequest req) {
    var e = repo.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proveedor no encontrado"));
    req.apply(e);
    try {
      e = repo.save(e);
      return ProveedorResponse.from(e);
    } catch (DataIntegrityViolationException ex) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "RUC de proveedor duplicado", ex);
    }
  }

  @Override
  public void eliminar(Long id) {
    if (!repo.existsById(id))
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Proveedor no encontrado");
    repo.deleteById(id);
  }

  @Override public 
  void activar(Long id)    { cambiarEstado(id, 1); }
  @Override public 
  void desactivar(Long id) { cambiarEstado(id, 0); }

  private void cambiarEstado(Long id, int valor) {
    Proveedor e = repo.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proveedor no encontrado"));
    e.setEstProveedor(valor);
    repo.save(e);
  }
}
