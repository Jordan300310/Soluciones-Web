package pe.fyj.fyj_erp_api.controller.catalog;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import pe.fyj.fyj_erp_api.models.catalog.Categoria;
import pe.fyj.fyj_erp_api.models.catalog.Marca;
import pe.fyj.fyj_erp_api.models.catalog.Producto;
import pe.fyj.fyj_erp_api.service.catalog.CatalogService;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {
  private final CatalogService service;
  public CatalogController(CatalogService service) { this.service = service; }

  @GetMapping("/brands")
  public List<Marca> brands() { return service.listBrands(); }

  @GetMapping("/categories")
  public List<Categoria> categories() { return service.listCategories(); }

  @GetMapping("/products")
  public Page<Producto> products(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "20") int size,
      @RequestParam(name = "brand_id", required = false) Long brandId,
      @RequestParam(name = "category_id", required = false) Long categoryId
  ) {
    return service.listProducts(page, size, brandId, categoryId);
  }
}