package pe.fyj.fyj_erp_api.shared;

public final class Estados {
  private Estados() {}
  public static int normalizeEstado(Integer nuevo, Integer actual) {
    return nuevo != null ? nuevo : (actual != null ? actual : 1);
  }
}