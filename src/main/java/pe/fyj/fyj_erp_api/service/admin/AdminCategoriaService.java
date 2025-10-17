package pe.fyj.fyj_erp_api.service.admin;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.fyj.fyj_erp_api.models.catalog.Categoria;
import pe.fyj.fyj_erp_api.repository.catalog.CategoriaRepository;

import java.util.List;

@Service
public class AdminCategoriaService {
private final CategoriaRepository repo;
public AdminCategoriaService(CategoriaRepository repo) { this.repo = repo; }

@Transactional 
public Categoria create(Categoria c) { return repo.save(c); }

@Transactional(readOnly = true) 
public List<Categoria> list() { return repo.findAll(); }

@Transactional(readOnly = true) 
public Categoria get(Long id) { return repo.findById(id).orElse(null); }

@Transactional 
public Categoria update(Long id, Categoria in) {
    Categoria db = repo.findById(id).orElse(null);
    if (db == null) return null;
    if (in.getNombre() != null) db.setNombre(in.getNombre());
    if (in.getEstado() != null) db.setEstado(in.getEstado());
    return repo.save(db);
}

@Transactional 
public void delete(Long id) { repo.deleteById(id); }
}