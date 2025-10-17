package pe.fyj.fyj_erp_api.service.admin;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import pe.fyj.fyj_erp_api.dto.admin.CreateEmpleadoRequest;
import pe.fyj.fyj_erp_api.dto.admin.UpdateEmpleadoRequest;
import pe.fyj.fyj_erp_api.models.auth.Persona;
import pe.fyj.fyj_erp_api.models.auth.Empleado;
import pe.fyj.fyj_erp_api.models.auth.Usuario;
import pe.fyj.fyj_erp_api.repository.auth.PersonaRepository;
import pe.fyj.fyj_erp_api.repository.auth.EmpleadoRepository;
import pe.fyj.fyj_erp_api.repository.auth.UsuarioRepository;

import java.util.List;

@Service
public class AdminEmpleadoService {

  private final PersonaRepository personaRepo;
  private final EmpleadoRepository empleadoRepo;
  private final UsuarioRepository usuarioRepo;
  private final PasswordEncoder passwordEncoder;

  public AdminEmpleadoService(PersonaRepository personaRepo,
                              EmpleadoRepository empleadoRepo,
                              UsuarioRepository usuarioRepo,
                              PasswordEncoder passwordEncoder) {
    this.personaRepo = personaRepo;
    this.empleadoRepo = empleadoRepo;
    this.usuarioRepo = usuarioRepo;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  public Empleado create(CreateEmpleadoRequest req) {
    // Duplicados mínimos
    if (personaRepo.existsByDniIgnoreCase(req.dni())) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "DNI ya registrado");
    }
    if (usuarioRepo.findByUsernameIgnoreCase(req.username()).isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Username ya registrado");
    }

    // Persona
    Persona p = req.toPersona();
    personaRepo.save(p);

    // Usuario (empleado)
    String hashed = passwordEncoder.encode(req.password());
    Usuario u = req.toUsuario(p, hashed);
    usuarioRepo.save(u);

    // Empleado
    Empleado e = req.toEmpleado(p);
    return empleadoRepo.save(e);
  }

  @Transactional(readOnly = true)
  public List<Empleado> list() {
    return empleadoRepo.findAll();
  }

  @Transactional(readOnly = true)
  public Empleado get(Long id) {
    return empleadoRepo.findById(id).orElse(null);
  }

  @Transactional
  public Empleado update(Long id, UpdateEmpleadoRequest req) {
    Empleado e = empleadoRepo.findById(id).orElse(null);
    if (e == null) return null;

    Persona p = e.getPersona();
    if (p != null) {
      if (req.nombres() != null) p.setNombres(req.nombres());
      if (req.apat() != null) p.setApat(req.apat());
      if (req.amat() != null) p.setAmat(req.amat());
      if (req.celular() != null) p.setCelular(req.celular());
      if (req.email() != null) p.setEmail(req.email());
      personaRepo.save(p);
    }

    if (req.cargo() != null) e.setCargo(req.cargo());
    if (req.sueldo() != null) e.setSueldo(req.sueldo());
    if (req.estado() != null) e.setEstado(req.estado());

    return empleadoRepo.save(e);
  }

  @Transactional
  public void delete(Long id) {
    empleadoRepo.deleteById(id);
  }
}