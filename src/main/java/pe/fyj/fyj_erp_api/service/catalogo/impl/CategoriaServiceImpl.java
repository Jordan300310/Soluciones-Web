package pe.fyj.fyj_erp_api.service.catalogo.impl;

import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

import pe.fyj.fyj_erp_api.dto.catalogo.*;
import pe.fyj.fyj_erp_api.repository.catalogo.CategoriaRepository;
import pe.fyj.fyj_erp_api.service.catalogo.CategoriaService;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

  private final CategoriaRepository repo;
  public CategoriaServiceImpl(CategoriaRepository repo) { this.repo = repo; }

  @Override public List<CategoriaResponse> listar() {
    return repo.findAll().stream().map(CategoriaResponse::from).toList();
  }

  @Override public CategoriaResponse obtener(Long idCategoria) {
    var c = repo.findById(idCategoria)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoría no encontrada"));
    return CategoriaResponse.from(c);
  }

  @Override public CategoriaResponse crear(CategoriaRequest r) {
    try {
      var c = repo.save(r.toEntity());
      return CategoriaResponse.from(c);
    } catch (DataIntegrityViolationException e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Nombre de categoría ya existe", e);
    }
  }

  @Override public CategoriaResponse actualizar(Long idCategoria, CategoriaRequest r) {
    var c = repo.findById(idCategoria)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoría no encontrada"));
    r.apply(c);
    try {
      c = repo.save(c);
      return CategoriaResponse.from(c);
    } catch (DataIntegrityViolationException e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Nombre de categoría ya existe", e);
    }
  }

  @Override public void eliminar(Long idCategoria) {
    if (!repo.existsById(idCategoria))
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoría no encontrada");
    repo.deleteById(idCategoria);
  }
}