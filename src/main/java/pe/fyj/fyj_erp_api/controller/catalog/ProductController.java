package pe.fyj.fyj_erp_api.controller.catalog;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.fyj.fyj_erp_api.dto.catalog.ProductAdminDTO;
import pe.fyj.fyj_erp_api.dto.catalog.ProductDTO;
import pe.fyj.fyj_erp_api.dto.catalog.ProductUpsertRequest;
import pe.fyj.fyj_erp_api.enums.ProductListRequest;
import pe.fyj.fyj_erp_api.security.AuthGuard;
import pe.fyj.fyj_erp_api.service.catalog.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

  private final ProductService service;
  public ProductController(ProductService service) { this.service = service; }

  // ===== PUBLIC =====
  @GetMapping
  public ResponseEntity<List<ProductDTO>> listPublic(@RequestParam(required = false) String q) {
    var list = service.list(ProductListRequest.PUBLIC, q, null).stream()
        .map(ProductDTO::from)
        .toList();
    return ResponseEntity.ok(list);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDTO> getPublic(@PathVariable Long id) {
    return ResponseEntity.ok(ProductDTO.from(service.getOneOrNotFound(id)));
  }

  // ===== CLIENT =====
  @GetMapping("/client")
  public ResponseEntity<List<ProductDTO>> listClient(@RequestParam(required = false) String q,
                                                     HttpSession session) {
    AuthGuard.requireCliente(session);
    var list = service.list(ProductListRequest.CLIENT, q, null).stream()
        .map(ProductDTO::from)
        .toList();
    return ResponseEntity.ok(list);
  }

  @GetMapping("/client/{id}")
  public ResponseEntity<ProductDTO> getClient(@PathVariable Long id, HttpSession session) {
    AuthGuard.requireCliente(session);
    return ResponseEntity.ok(ProductDTO.from(service.getOneOrNotFound(id)));
  }

  // ===== ADMIN (CRUD) =====
  @GetMapping("/admin")
  public ResponseEntity<List<ProductAdminDTO>> listAdmin(@RequestParam(required = false) String q,
                                                         @RequestParam(required = false) Boolean estado,
                                                         HttpSession session) {
    AuthGuard.requireEmpleado(session);
    var list = service.list(ProductListRequest.ADMIN, q, estado).stream()
        .map(ProductAdminDTO::from)
        .toList();
    return ResponseEntity.ok(list);
  }

  @GetMapping("/admin/{id}")
  public ResponseEntity<ProductAdminDTO> getAdmin(@PathVariable Long id, HttpSession session) {
    AuthGuard.requireEmpleado(session);
    return ResponseEntity.ok(ProductAdminDTO.from(service.getOneOrNotFound(id)));
  }

  @PostMapping("/admin")
  public ResponseEntity<ProductAdminDTO> createAdmin(@Valid @RequestBody ProductUpsertRequest req,
                                                     HttpSession session) {
    AuthGuard.requireEmpleado(session);
    var saved = service.create(req);
    return ResponseEntity.status(201).body(ProductAdminDTO.from(saved));
  }

  @PutMapping("/admin/{id}")
  public ResponseEntity<ProductAdminDTO> updateAdmin(@PathVariable Long id,
                                                     @Valid @RequestBody ProductUpsertRequest req,
                                                     HttpSession session) {
    AuthGuard.requireEmpleado(session);
    var updated = service.update(id, req);
    return ResponseEntity.ok(ProductAdminDTO.from(updated));
  }

  @PatchMapping("/admin/{id}/estado")
  public ResponseEntity<Void> changeEstadoAdmin(@PathVariable Long id,
                                                @RequestParam boolean estado,
                                                HttpSession session) {
    AuthGuard.requireEmpleado(session);
    service.changeEstado(id, estado);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/admin/{id}")
  public ResponseEntity<Void> deleteAdmin(@PathVariable Long id, HttpSession session) {
    AuthGuard.requireEmpleado(session);
    service.deleteLogical(id);
    return ResponseEntity.noContent().build();
  }
}
