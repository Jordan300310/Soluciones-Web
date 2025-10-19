package pe.fyj.fyj_erp_api.service.admin;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.fyj.fyj_erp_api.dto.ticket.ChangeEstadoTicketRequest;
import pe.fyj.fyj_erp_api.dto.ticket.CreateAtencionRequest;
import pe.fyj.fyj_erp_api.dto.ticket.TicketResponse;
import pe.fyj.fyj_erp_api.models.ticket.AtencionTicket;
import pe.fyj.fyj_erp_api.models.ticket.EstadoTicket;
import pe.fyj.fyj_erp_api.models.ticket.Ticket;
import pe.fyj.fyj_erp_api.repository.ticket.AtencionTicketRepository;
import pe.fyj.fyj_erp_api.repository.ticket.EstadoTicketRepository;
import pe.fyj.fyj_erp_api.repository.ticket.TicketRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminTicketService {
  private final TicketRepository ticketRepo;
  private final AtencionTicketRepository atencionRepo;
  private final EstadoTicketRepository estadoRepo;

  public AdminTicketService(TicketRepository ticketRepo,
                            AtencionTicketRepository atencionRepo,
                            EstadoTicketRepository estadoRepo) {
    this.ticketRepo = ticketRepo;
    this.atencionRepo = atencionRepo;
    this.estadoRepo = estadoRepo;
  }

  @Transactional(readOnly = true)
  public List<Ticket> list() { return ticketRepo.findAll(); }

  @Transactional(readOnly = true)
  public TicketResponse get(Long id) {
    Ticket t = ticketRepo.findById(id).orElse(null);
    if (t == null) return null;
    String estado = estadoRepo.findById(t.getIdEstadoTicket()).map(EstadoTicket::getNombreEstado).orElse(null);
    var ats = atencionRepo.findByIdTicket(id).stream()
        .map(a -> new TicketResponse.Atencion(a.getIdEmpleado(), a.getFechaAtencion(), a.getObservaciones()))
        .toList();
    return new TicketResponse(t.getId(), t.getFechaCreacion(), estado, t.getAsunto(), t.getDescripcion(), ats);
  }

  @Transactional
  public boolean changeEstado(Long idTicket, ChangeEstadoTicketRequest req) {
    Ticket t = ticketRepo.findById(idTicket).orElse(null);
    if (t == null) return false;
    EstadoTicket e = estadoRepo.findByNombreEstadoIgnoreCase(req.estado()).orElse(null);
    if (e == null) return false;
    t.setIdEstadoTicket(e.getId());
    ticketRepo.save(t);
    return true;
  }

  @Transactional
  public boolean addAtencion(Long idTicket, Long idEmpleado, CreateAtencionRequest req) {
    Ticket t = ticketRepo.findById(idTicket).orElse(null);
    if (t == null) return false;
    var a = AtencionTicket.builder()
        .idTicket(idTicket)
        .idEmpleado(idEmpleado)
        .fechaAtencion(LocalDateTime.now())
        .observaciones(req.observaciones())
        .build();
    atencionRepo.save(a);
    return true;
  }
}