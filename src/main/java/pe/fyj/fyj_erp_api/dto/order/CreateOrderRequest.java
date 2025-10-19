package pe.fyj.fyj_erp_api.dto.order;

import java.util.List;

public record CreateOrderRequest(
  List<Item> items
) {
  public record Item(Long id_producto, Integer cantidad) {}
}