package pe.fyj.fyj_erp_api.service.me;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import pe.fyj.fyj_erp_api.dto.me.ChangePasswordRequest;
import pe.fyj.fyj_erp_api.dto.me.ProfileResponse;
import pe.fyj.fyj_erp_api.dto.me.UpdateProfileRequest;
import pe.fyj.fyj_erp_api.models.auth.Cliente;
import pe.fyj.fyj_erp_api.models.auth.Persona;
import pe.fyj.fyj_erp_api.models.auth.Usuario;
import pe.fyj.fyj_erp_api.models.order.Pedido;
import pe.fyj.fyj_erp_api.models.payment.Pago;
import pe.fyj.fyj_erp_api.models.ticket.Ticket;
import pe.fyj.fyj_erp_api.repository.auth.ClienteRepository;
import pe.fyj.fyj_erp_api.repository.auth.PersonaRepository;
import pe.fyj.fyj_erp_api.repository.auth.UsuarioRepository;
import pe.fyj.fyj_erp_api.repository.order.PedidoRepository;
import pe.fyj.fyj_erp_api.repository.payment.PagoRepository;
import pe.fyj.fyj_erp_api.repository.ticket.TicketRepository;

import java.util.List;

@Service
public class MyAccountService {

  private final PersonaRepository personaRepo;
  private final ClienteRepository clienteRepo;
  private final UsuarioRepository usuarioRepo;
  private final PedidoRepository pedidoRepo;
  private final PagoRepository pagoRepo;
  private final TicketRepository ticketRepo;
  private final PasswordEncoder passwordEncoder;

  public MyAccountService(PersonaRepository personaRepo,
                          ClienteRepository clienteRepo,
                          UsuarioRepository usuarioRepo,
                          PedidoRepository pedidoRepo,
                          PagoRepository pagoRepo,
                          TicketRepository ticketRepo,
                          PasswordEncoder passwordEncoder) {
    this.personaRepo = personaRepo;
    this.clienteRepo = clienteRepo;
    this.usuarioRepo = usuarioRepo;
    this.pedidoRepo = pedidoRepo;
    this.pagoRepo = pagoRepo;
    this.ticketRepo = ticketRepo;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional(readOnly = true)
  public ProfileResponse getProfile(Long idPersona) {
    Persona p = personaRepo.findById(idPersona).orElseThrow();
    Boolean activo = clienteRepo.findByPersonaId(idPersona).map(Cliente::getEstado).orElse(null);
    return new ProfileResponse(
        p.getId(), p.getNombres(), p.getApat(), p.getAmat(),
        p.getDni(), p.getCelular(), p.getEmail(), p.getFechaNacimiento(),
        activo
    );
  }

  @Transactional
  public ProfileResponse updateProfile(Long idPersona, UpdateProfileRequest req) {
    Persona p = personaRepo.findById(idPersona).orElseThrow();
    if (req.nombres() != null) p.setNombres(req.nombres());
    if (req.apat()    != null) p.setApat(req.apat());
    if (req.amat()    != null) p.setAmat(req.amat());
    if (req.celular() != null) p.setCelular(req.celular());
    if (req.email()   != null) p.setEmail(req.email());
    personaRepo.save(p);
    return getProfile(idPersona);
  }

  @Transactional
  public void changePassword(Long idPersona, ChangePasswordRequest req) {
    Usuario u = usuarioRepo.findByPersonaId(idPersona)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
    if (!passwordEncoder.matches(req.passwordActual(), u.getPassword())) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password actual incorrecto");
    }
    u.setPassword(passwordEncoder.encode(req.passwordNueva()));
    usuarioRepo.save(u);
  }

  @Transactional(readOnly = true)
  public List<Pedido> myOrders(Long idPersonaCliente) {
    Long idCliente = clienteRepo.findByPersonaId(idPersonaCliente).orElseThrow().getId();
    return pedidoRepo.findByIdCliente(idCliente);
  }

  @Transactional(readOnly = true)
  public Pedido myOrderById(Long idPersonaCliente, Long idPedido) {
    Long idCliente = clienteRepo.findByPersonaId(idPersonaCliente).orElseThrow().getId();
    Pedido p = pedidoRepo.findById(idPedido).orElse(null);
    if (p == null || !p.getIdCliente().equals(idCliente)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return p;
  }

  @Transactional(readOnly = true)
  public List<Pago> myPayments(Long idPersonaCliente) {
    Long idCliente = clienteRepo.findByPersonaId(idPersonaCliente).orElseThrow().getId();
    var pedidos = pedidoRepo.findByIdCliente(idCliente);
    var ids = pedidos.stream().map(Pedido::getId).toList();
    return ids.isEmpty() ? List.of() : pagoRepo.findByIdPedidoIn(ids);
  }

  @Transactional(readOnly = true)
  public List<Ticket> myTickets(Long idPersonaCliente) {
    Long idCliente = clienteRepo.findByPersonaId(idPersonaCliente).orElseThrow().getId();
    return ticketRepo.findByIdCliente(idCliente);
  }

  @Transactional(readOnly = true)
  public Ticket myTicketById(Long idPersonaCliente, Long idTicket) {
    Long idCliente = clienteRepo.findByPersonaId(idPersonaCliente).orElseThrow().getId();
    Ticket t = ticketRepo.findById(idTicket).orElse(null);
    if (t == null || !t.getIdCliente().equals(idCliente)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return t;
  }
}