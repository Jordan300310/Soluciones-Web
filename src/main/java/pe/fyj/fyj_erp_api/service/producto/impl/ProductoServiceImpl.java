package pe.fyj.fyj_erp_api.service.producto.impl;

import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

import pe.fyj.fyj_erp_api.dto.producto.*;
import pe.fyj.fyj_erp_api.entity.catalogo.Categoria;
import pe.fyj.fyj_erp_api.entity.catalogo.Marca;
import pe.fyj.fyj_erp_api.repository.catalogo.CategoriaRepository;
import pe.fyj.fyj_erp_api.repository.catalogo.MarcaRepository;
import pe.fyj.fyj_erp_api.repository.producto.ProductoRepository;
import pe.fyj.fyj_erp_api.service.producto.ProductoService;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {

  private final ProductoRepository repo;
  private final CategoriaRepository catRepo;
  private final MarcaRepository marcaRepo;

  public ProductoServiceImpl(ProductoRepository repo, CategoriaRepository catRepo, MarcaRepository marcaRepo) {
    this.repo = repo; this.catRepo = catRepo; this.marcaRepo = marcaRepo;
  }

  @Override
  public List<ProductoResponse> listar() {
    return repo.findAll().stream().map(ProductoResponse::from).toList();
  }

  @Override
  public ProductoResponse obtener(Long idProducto) {
    var p = repo.findById(idProducto)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
    return ProductoResponse.from(p);
  }

  @Override
  public ProductoResponse crear(ProductoRequest req) {
    var cat = catRepo.findById(req.idCategoria())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoría inválida"));
    var mar = marcaRepo.findById(req.idMarca())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Marca inválida"));

    try {
      var p = repo.save(req.toEntity(cat, mar));
      return ProductoResponse.from(p);
    } catch (DataIntegrityViolationException e) {
      // Confianza en la restricción UNIQUE(cod_producto)
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Código de producto duplicado", e);
    }
  }

  @Override
  public ProductoResponse actualizar(Long idProducto, ProductoRequest req) {
    var p = repo.findById(idProducto)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));

    var cat = catRepo.findById(req.idCategoria())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoría inválida"));
    var mar = marcaRepo.findById(req.idMarca())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Marca inválida"));

    req.apply(p, cat, mar);

    try {
      p = repo.save(p);
      return ProductoResponse.from(p);
    } catch (DataIntegrityViolationException e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Código de producto duplicado", e);
    }
  }

  @Override
  public void eliminar(Long idProducto) {
    if (!repo.existsById(idProducto))
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
    repo.deleteById(idProducto);
  }
}