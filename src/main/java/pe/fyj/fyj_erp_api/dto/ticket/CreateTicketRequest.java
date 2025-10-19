package pe.fyj.fyj_erp_api.dto.ticket;

public record CreateTicketRequest(
  String asunto,
  String descripcion
) {}