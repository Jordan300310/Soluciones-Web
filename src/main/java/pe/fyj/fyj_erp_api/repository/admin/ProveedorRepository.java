package pe.fyj.fyj_erp_api.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.fyj.fyj_erp_api.models.purchase.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
}