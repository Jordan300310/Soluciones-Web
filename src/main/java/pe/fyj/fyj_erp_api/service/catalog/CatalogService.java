package pe.fyj.fyj_erp_api.service.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.fyj.fyj_erp_api.models.catalog.Marca;
import pe.fyj.fyj_erp_api.models.catalog.Producto;
import pe.fyj.fyj_erp_api.models.catalog.Categoria;
import pe.fyj.fyj_erp_api.repository.catalog.MarcaRepository;
import pe.fyj.fyj_erp_api.repository.catalog.ProductoRepository;
import pe.fyj.fyj_erp_api.repository.catalog.CategoriaRepository;

import java.util.List;

@Service
public class CatalogService {
private final MarcaRepository marcaRepo;
private final CategoriaRepository categoriaRepo;
private final ProductoRepository productoRepo;


public CatalogService(MarcaRepository marcaRepo, CategoriaRepository categoriaRepo, ProductoRepository productoRepo) {
    this.marcaRepo = marcaRepo;
    this.categoriaRepo = categoriaRepo;
    this.productoRepo = productoRepo;
}

@Transactional(readOnly = true)
public List<Marca> listBrands() {
    return marcaRepo.findByEstadoTrueOrderByNombreAsc();
}

@Transactional(readOnly = true)
public List<Categoria> listCategories() {
    return categoriaRepo.findByEstadoTrueOrderByNombreAsc();
}

public Page<Producto> listProducts(int page, int size, Long brandId, Long categoryId) {
    var p = PageRequest.of(page, size);
    if (brandId != null && categoryId != null) return productoRepo.findByEstadoTrueAndIdMarcaAndIdCategoria(brandId, categoryId, p);
    if (brandId != null) return productoRepo.findByEstadoTrueAndIdMarca(brandId, p);
    if (categoryId != null) return productoRepo.findByEstadoTrueAndIdCategoria(categoryId, p);
    return productoRepo.findByEstadoTrue(p);
  }
}