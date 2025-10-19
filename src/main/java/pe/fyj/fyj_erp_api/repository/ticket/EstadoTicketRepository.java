package pe.fyj.fyj_erp_api.repository.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.fyj.fyj_erp_api.models.ticket.EstadoTicket;
import java.util.Optional;

public interface EstadoTicketRepository extends JpaRepository<EstadoTicket, Long> {
  Optional<EstadoTicket> findByNombreEstadoIgnoreCase(String nombreEstado);
}