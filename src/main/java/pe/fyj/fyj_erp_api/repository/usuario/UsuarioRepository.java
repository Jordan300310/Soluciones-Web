package pe.fyj.fyj_erp_api.repository.usuario;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.fyj.fyj_erp_api.entity.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  Optional<Usuario> findByUsername(String username);
}
