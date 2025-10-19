package pe.fyj.fyj_erp_api.service.ticket;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.fyj.fyj_erp_api.dto.ticket.CreateTicketRequest;
import pe.fyj.fyj_erp_api.dto.ticket.TicketResponse;
import pe.fyj.fyj_erp_api.models.auth.Cliente;
import pe.fyj.fyj_erp_api.models.ticket.Ticket;
import pe.fyj.fyj_erp_api.models.ticket.EstadoTicket;
import pe.fyj.fyj_erp_api.repository.auth.ClienteRepository;
import pe.fyj.fyj_erp_api.repository.ticket.AtencionTicketRepository;
import pe.fyj.fyj_erp_api.repository.ticket.EstadoTicketRepository;
import pe.fyj.fyj_erp_api.repository.ticket.TicketRepository;

import java.time.LocalDateTime;

@Service
public class TicketService {
  private final TicketRepository ticketRepo;
  private final AtencionTicketRepository atencionRepo;
  private final EstadoTicketRepository estadoRepo;
  private final ClienteRepository clienteRepo;

  public TicketService(TicketRepository ticketRepo,
                       AtencionTicketRepository atencionRepo,
                       EstadoTicketRepository estadoRepo,
                       ClienteRepository clienteRepo) {
    this.ticketRepo = ticketRepo;
    this.atencionRepo = atencionRepo;
    this.estadoRepo = estadoRepo;
    this.clienteRepo = clienteRepo;
  }

  @Transactional
  public TicketResponse create(Long idPersonaCliente, CreateTicketRequest req) {
    Cliente c = clienteRepo.findByPersonaId(idPersonaCliente).orElseThrow();
    EstadoTicket estadoIni = estadoRepo.findByNombreEstadoIgnoreCase("ABIERTO").orElseThrow();

    Ticket t = Ticket.builder()
        .idCliente(c.getId())
        .asunto(req.asunto())
        .descripcion(req.descripcion())
        .fechaCreacion(LocalDateTime.now())
        .idEstadoTicket(estadoIni.getId())
        .build();
    ticketRepo.save(t);

    return get(t.getId());
  }

  @Transactional(readOnly = true)
  public TicketResponse get(Long idTicket) {
    Ticket t = ticketRepo.findById(idTicket).orElseThrow();
    String estado = estadoRepo.findById(t.getIdEstadoTicket()).map(EstadoTicket::getNombreEstado).orElse(null);
    var ats = atencionRepo.findByIdTicket(idTicket).stream()
        .map(a -> new TicketResponse.Atencion(a.getIdEmpleado(), a.getFechaAtencion(), a.getObservaciones()))
        .toList();
    return new TicketResponse(t.getId(), t.getFechaCreacion(), estado, t.getAsunto(), t.getDescripcion(), ats);
  }
}