package pe.fyj.fyj_erp_api.repository.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.fyj.fyj_erp_api.models.ticket.Ticket;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
  List<Ticket> findByIdCliente(Long idCliente);
}