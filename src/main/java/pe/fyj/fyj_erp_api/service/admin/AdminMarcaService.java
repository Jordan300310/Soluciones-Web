package pe.fyj.fyj_erp_api.service.admin;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.fyj.fyj_erp_api.models.catalog.Marca;
import pe.fyj.fyj_erp_api.repository.catalog.MarcaRepository;

import java.util.List;

@Service
public class AdminMarcaService {
private final MarcaRepository repo;
public AdminMarcaService(MarcaRepository repo) { this.repo = repo; }

@Transactional 
public Marca create(Marca m) { return repo.save(m); }

@Transactional(readOnly = true) 
public List<Marca> list() { return repo.findAll(); }

@Transactional(readOnly = true) 
public Marca get(Long id) { return repo.findById(id).orElse(null); }

@Transactional 
public Marca update(Long id, Marca in) {
    Marca db = repo.findById(id).orElse(null);
    if (db == null) return null;
    if (in.getNombre() != null) db.setNombre(in.getNombre());
    if (in.getEstado() != null) db.setEstado(in.getEstado());
    return repo.save(db);
}

@Transactional 
public void delete(Long id) { repo.deleteById(id); }
}