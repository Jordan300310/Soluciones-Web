package pe.fyj.fyj_erp_api.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import pe.fyj.fyj_erp_api.models.catalog.Producto;
import pe.fyj.fyj_erp_api.service.admin.AdminProductoService;
import pe.fyj.fyj_erp_api.dto.admin.ProductAdminDTO;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin/productos")
public class AdminProductoController {

  private final AdminProductoService service;
  public AdminProductoController(AdminProductoService service) { this.service = service; }

  @PostMapping
  public ResponseEntity<Producto> create(@RequestBody Producto body) {
    var p = service.create(body);
    return ResponseEntity.created(URI.create("/admin/productos/" + p.getId())).body(p);
  }

  @GetMapping
  @Transactional(readOnly = true)
  public List<ProductAdminDTO> list() {
    return service.list().stream().map(ProductAdminDTO::from).toList();
  }

  @GetMapping("/{id}")
  @Transactional(readOnly = true)
  public ResponseEntity<ProductAdminDTO> get(@PathVariable Long id) {
    var p = service.get(id);
    return (p == null) ? ResponseEntity.notFound().build()
                       : ResponseEntity.ok(ProductAdminDTO.from(p));
  }

 @PatchMapping("/{id}")
  @Transactional
  public ResponseEntity<ProductAdminDTO> update(@PathVariable Long id, @RequestBody Producto body) {
    var p = service.update(id, body);        
    if (p == null) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(ProductAdminDTO.from(p));  
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}