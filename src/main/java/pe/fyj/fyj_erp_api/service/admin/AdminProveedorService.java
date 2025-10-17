package pe.fyj.fyj_erp_api.service.admin;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.fyj.fyj_erp_api.dto.admin.CreateProveedorRequest;
import pe.fyj.fyj_erp_api.dto.admin.UpdateProveedorRequest;
import pe.fyj.fyj_erp_api.models.auth.Persona;
import pe.fyj.fyj_erp_api.models.catalog.Proveedor;
import pe.fyj.fyj_erp_api.repository.auth.PersonaRepository;
import pe.fyj.fyj_erp_api.repository.catalog.ProveedorRepository;

import java.util.List;

@Service
public class AdminProveedorService {
  private final ProveedorRepository repo;
  private final PersonaRepository personaRepo;

  public AdminProveedorService(ProveedorRepository repo, PersonaRepository personaRepo) {
    this.repo = repo;
    this.personaRepo = personaRepo;
  }

  @Transactional
  public Proveedor create(CreateProveedorRequest req) {

    Persona persona = req.toPersona();
    personaRepo.save(persona);
    Proveedor prov = req.toProveedor(persona);
    return repo.save(prov);
  }

  @Transactional(readOnly = true) 
  public List<Proveedor> list() {
     return repo.findAll(); }

  @Transactional(readOnly = true) 
  public Proveedor get(Long id) {
     return repo.findById(id).orElse(null); }

    @Transactional
  public Proveedor update(Long id, UpdateProveedorRequest req) {
    Proveedor db = repo.findById(id).orElse(null);
    if (db == null) return null;


    if (db.getPersona() != null) {
      personaRepo.findById(db.getPersona().getId()).ifPresent(p -> {
        if (req.nombres() != null) p.setNombres(req.nombres());
        if (req.apat() != null)    p.setApat(req.apat());
        if (req.amat() != null)    p.setAmat(req.amat());
        if (req.celular() != null) p.setCelular(req.celular());
        if (req.email() != null)   p.setEmail(req.email());
        personaRepo.save(p);
      });
    }

    if (req.razonSocial() != null) db.setRazonSocial(req.razonSocial());
    if (req.ruc() != null)         db.setRuc(req.ruc());
    if (req.estado() != null)      db.setEstado(req.estado());

    return repo.save(db);
  }
  @Transactional 
  public void delete(Long id) {
     repo.deleteById(id); }
}
