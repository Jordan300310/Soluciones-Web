package pe.fyj.fyj_erp_api.controller.producto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import pe.fyj.fyj_erp_api.dto.producto.*;
import pe.fyj.fyj_erp_api.service.producto.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

  private final ProductoService service;
  public ProductoController(ProductoService service) { this.service = service; }

  @GetMapping
  public List<ProductoResponse> listar() {
    return service.listar();
  }

  @GetMapping("/{idProducto}")
  public ProductoResponse obtener(@PathVariable Long idProducto) {
    return service.obtener(idProducto);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ProductoResponse crear(@RequestBody ProductoRequest req) {
    return service.crear(req);
  }

  @PutMapping("/{idProducto}")
  public ProductoResponse actualizar(@PathVariable Long idProducto, @RequestBody ProductoRequest req) {
    return service.actualizar(idProducto, req);
  }

  @DeleteMapping("/{idProducto}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void eliminar(@PathVariable Long idProducto) {
    service.eliminar(idProducto);
  }
}