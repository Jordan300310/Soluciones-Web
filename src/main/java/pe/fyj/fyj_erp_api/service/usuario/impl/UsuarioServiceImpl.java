package pe.fyj.fyj_erp_api.service.usuario.impl;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import pe.fyj.fyj_erp_api.dto.usuario.*;
import pe.fyj.fyj_erp_api.entity.persona.Cliente;
import pe.fyj.fyj_erp_api.entity.persona.Empleado;
import pe.fyj.fyj_erp_api.entity.persona.Persona;
import pe.fyj.fyj_erp_api.entity.usuario.Usuario;
import pe.fyj.fyj_erp_api.repository.persona.ClienteRepository;
import pe.fyj.fyj_erp_api.repository.persona.EmpleadoRepository;
import pe.fyj.fyj_erp_api.repository.persona.PersonaRepository;
import pe.fyj.fyj_erp_api.repository.usuario.UsuarioRepository;
import pe.fyj.fyj_erp_api.service.usuario.UsuarioService;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

  private final UsuarioRepository usuarioRepo;
  private final PersonaRepository personaRepo;
  private final ClienteRepository clienteRepo;
  private final EmpleadoRepository empleadoRepo;
  private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  public UsuarioServiceImpl(
      UsuarioRepository usuarioRepo,
      PersonaRepository personaRepo,
      ClienteRepository clienteRepo,
      EmpleadoRepository empleadoRepo) {
    this.usuarioRepo = usuarioRepo;
    this.personaRepo = personaRepo;
    this.clienteRepo = clienteRepo;
    this.empleadoRepo = empleadoRepo;
  }
  @Override
  public List<UsuarioResponse> listar() {
    return usuarioRepo.findAll().stream().map(UsuarioResponse::from).toList();
  }
  @Override
  public UsuarioResponse registrarCliente(UsuarioRequest req) {
    Persona p = personaRepo.findById(req.idPersona())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Persona no existe"));

    try {
      Usuario u = usuarioRepo.save(req.toEntity("CLI", encoder.encode(req.password())));
      if (!clienteRepo.existsById(p.getIdPersona())) {
        Cliente c = new Cliente();
        c.setPersona(p);
        clienteRepo.save(c);
      }
      return UsuarioResponse.from(u);
    } catch (DataIntegrityViolationException e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Username o persona ya registrados", e);
    }
  }
  @Override
  public UsuarioResponse registrarEmpleado(UsuarioRequest req) {
    Persona p = personaRepo.findById(req.idPersona())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Persona no existe"));

    try {
      Usuario u = usuarioRepo.save(req.toEntity("EMP", encoder.encode(req.password())));
      if (!empleadoRepo.existsById(p.getIdPersona())) {
        Empleado e = new Empleado();
        e.setPersona(p);
        empleadoRepo.save(e);
      }
      return UsuarioResponse.from(u);
    } catch (DataIntegrityViolationException e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Username o persona ya registrados", e);
    }
  }
  @Override
  public UsuarioResponse login(LoginRequest req) {
    Usuario u = usuarioRepo.findByUsername(req.username())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas"));
    if (u.getEstado() != 1 || !encoder.matches(req.password(), u.getPasswordHash()))
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
    return UsuarioResponse.from(u); 
  }
}