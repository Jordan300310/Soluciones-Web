package pe.fyj.fyj_erp_api.serviceimpl.auth;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pe.fyj.fyj_erp_api.dto.auth.LoginRequest;
import pe.fyj.fyj_erp_api.dto.auth.RegisterRequest;
import pe.fyj.fyj_erp_api.dto.auth.RegisterResponse;
import pe.fyj.fyj_erp_api.dto.auth.SessionUserDTO;
import pe.fyj.fyj_erp_api.models.auth.*;
import pe.fyj.fyj_erp_api.repository.auth.ClienteRepository;
import pe.fyj.fyj_erp_api.repository.auth.EmpleadoRepository;
import pe.fyj.fyj_erp_api.repository.auth.PersonaRepository;
import pe.fyj.fyj_erp_api.repository.auth.UsuarioRepository;
import pe.fyj.fyj_erp_api.service.auth.AuthService;


@Service
public class AuthServiceImpl implements AuthService {

  public static final String SESSION_KEY = "SESSION_USER";

  private final UsuarioRepository usuarioRepository;
  private final PersonaRepository personaRepository;
  private final ClienteRepository clienteRepository;
  private final EmpleadoRepository empleadoRepository;
  private final PasswordEncoder passwordEncoder;

  public AuthServiceImpl(UsuarioRepository usuarioRepository,
                         PersonaRepository personaRepository,
                         ClienteRepository clienteRepository,
                         EmpleadoRepository empleadoRepository,
                         PasswordEncoder passwordEncoder) {
    this.usuarioRepository = usuarioRepository;
    this.personaRepository = personaRepository;
    this.clienteRepository = clienteRepository;
    this.empleadoRepository = empleadoRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  @Transactional
  public RegisterResponse register(RegisterRequest request) {
    if (personaRepository.existsByDniIgnoreCase(request.dni())) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "DNI ya registrado");
    }

    Persona persona = request.toPersona();
    personaRepository.save(persona);
    Cliente cliente = request.toCliente(persona);
    clienteRepository.save(cliente);
    String hashed = passwordEncoder.encode(request.password());
    Usuario usuario = request.toUsuario(persona, hashed);
    usuarioRepository.save(usuario);

    return new RegisterResponse("Registro exitoso", request.username(), "cliente");
  }

  @Override
  @Transactional
  public void login(LoginRequest request, HttpSession session) {
    Usuario user = usuarioRepository.findByUsernameIgnoreCase(request.username())
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas"));

    if (!passwordEncoder.matches(request.password(), user.getPassword())) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
    }

    String tipo = user.getTipo() == null ? null : user.getTipo().trim().toLowerCase();

    boolean enabled;
    if ("cliente".equals(tipo)) {
      Cliente cli = clienteRepository.findByPersonaId(user.getPersona().getId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.LOCKED, "Cliente sin ficha activa"));
      enabled = Boolean.TRUE.equals(cli.getEstado());
      if (!enabled) throw new ResponseStatusException(HttpStatus.LOCKED, "Cliente inactivo");
    } else if ("empleado".equals(tipo)) {
      Empleado emp = empleadoRepository.findByPersonaId(user.getPersona().getId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.LOCKED, "Empleado sin ficha activa"));
      enabled = Boolean.TRUE.equals(emp.getEstado());
      if (!enabled) throw new ResponseStatusException(HttpStatus.LOCKED, "Empleado inactivo");
    } else {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Tipo de usuario no permitido");
    }

    var su = SessionUserDTO.from(user, enabled).toSessionUser();
    session.setAttribute(SESSION_KEY, su);
  }

  @Override
  public void logout(HttpSession session) {
    session.invalidate();
  }
}

