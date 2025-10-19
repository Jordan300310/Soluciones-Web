package pe.fyj.fyj_erp_api.service.admin;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.fyj.fyj_erp_api.dto.admin.UpdateClienteRequest;
import pe.fyj.fyj_erp_api.models.auth.Cliente;
import pe.fyj.fyj_erp_api.models.auth.Persona;
import pe.fyj.fyj_erp_api.repository.auth.ClienteRepository;
import pe.fyj.fyj_erp_api.repository.auth.PersonaRepository;

import java.util.List;

@Service
public class AdminClienteService {

  private final ClienteRepository clienteRepo;
  private final PersonaRepository personaRepo;

  public AdminClienteService(ClienteRepository clienteRepo, PersonaRepository personaRepo) {
    this.clienteRepo = clienteRepo;
    this.personaRepo = personaRepo;
  }

  @Transactional(readOnly = true)
  public List<Cliente> list() {
    return clienteRepo.findAll();
  }

  @Transactional(readOnly = true)
  public Cliente get(Long id) {
    return clienteRepo.findById(id).orElse(null);
  }

  @Transactional
  public Cliente update(Long id, UpdateClienteRequest req) {
    Cliente c = clienteRepo.findById(id).orElse(null);
    if (c == null) return null;

    Persona p = c.getPersona();
    if (p != null) {
      if (req.nombres() != null) p.setNombres(req.nombres());
      if (req.apat()    != null) p.setApat(req.apat());
      if (req.amat()    != null) p.setAmat(req.amat());
      if (req.celular() != null) p.setCelular(req.celular());
      if (req.email()   != null) p.setEmail(req.email());
      personaRepo.save(p);
    }

    if (req.estado() != null) c.setEstado(req.estado());
    return clienteRepo.save(c);
  }

  @Transactional
  public void delete(Long id) {
    clienteRepo.deleteById(id);
  }
}