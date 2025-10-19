package pe.fyj.fyj_erp_api.repository.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.fyj.fyj_erp_api.models.ticket.AtencionTicket;
import java.util.List;

public interface AtencionTicketRepository extends JpaRepository<AtencionTicket, Long> {
  List<AtencionTicket> findByIdTicket(Long idTicket);
}