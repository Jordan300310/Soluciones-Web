package pe.fyj.fyj_erp_api.service.marca.impl;

import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

import pe.fyj.fyj_erp_api.dto.marca.*;
import pe.fyj.fyj_erp_api.repository.catalogo.MarcaRepository;
import pe.fyj.fyj_erp_api.service.marca.MarcaService;

@Service
@Transactional
public class MarcaServiceImpl implements MarcaService {

  private final MarcaRepository repo;

  public MarcaServiceImpl(MarcaRepository repo) { this.repo = repo; }

  @Override
  public List<MarcaResponse> listar() {
    return repo.findAll().stream().map(MarcaResponse::from).toList();
  }

  @Override
  public MarcaResponse obtener(Long idMarca) {
    var m = repo.findById(idMarca)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Marca no encontrada"));
    return MarcaResponse.from(m);
  }

  @Override
  public MarcaResponse crear(MarcaRequest req) {
    try {
      var m = repo.save(req.toEntity());
      return MarcaResponse.from(m);
    } catch (DataIntegrityViolationException e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Nombre de marca ya registrado", e);
    }
  }

  @Override
  public MarcaResponse actualizar(Long idMarca, MarcaRequest req) {
    var m = repo.findById(idMarca)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Marca no encontrada"));
    req.apply(m);
    try {
      m = repo.save(m);
      return MarcaResponse.from(m);
    } catch (DataIntegrityViolationException e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Nombre de marca ya registrado", e);
    }
  }

  @Override
  public void eliminar(Long idMarca) {
    if (!repo.existsById(idMarca))
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Marca no encontrada");
    repo.deleteById(idMarca);
  }
}