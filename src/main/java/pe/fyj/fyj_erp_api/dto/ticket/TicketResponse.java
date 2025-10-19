package pe.fyj.fyj_erp_api.dto.ticket;

import java.time.LocalDateTime;
import java.util.List;

public record TicketResponse(
  Long id_ticket,
  LocalDateTime fecha_creacion,
  String estado,
  String asunto,
  String descripcion,
  List<Atencion> atenciones
) {
  public record Atencion(Long id_empleado, LocalDateTime fecha_atencion, String observaciones) {}
}