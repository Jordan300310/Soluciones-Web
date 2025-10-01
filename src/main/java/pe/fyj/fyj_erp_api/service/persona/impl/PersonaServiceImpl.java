package pe.fyj.fyj_erp_api.service.persona.impl;

import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

import pe.fyj.fyj_erp_api.dto.persona.PersonaRequest;
import pe.fyj.fyj_erp_api.dto.persona.PersonaResponse;
import pe.fyj.fyj_erp_api.repository.persona.PersonaRepository;
import pe.fyj.fyj_erp_api.service.persona.PersonaService;

@Service
@Transactional
public class PersonaServiceImpl implements PersonaService {

  private final PersonaRepository personaRepo;

  public PersonaServiceImpl(PersonaRepository personaRepo) { this.personaRepo = personaRepo; }

  @Override
  public List<PersonaResponse> listar() {
    return personaRepo.findAll().stream().map(PersonaResponse::from).toList();
  }

  @Override
  public PersonaResponse obtener(Long idPersona) {
    var p = personaRepo.findById(idPersona)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Persona no encontrada"));
    return PersonaResponse.from(p);
  }

  @Override
  public PersonaResponse crear(PersonaRequest r) {
    try {
      var p = personaRepo.save(r.toEntity());
      return PersonaResponse.from(p);
    } catch (DataIntegrityViolationException e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "DNI ya existe", e);
    }
  }

  @Override
  public PersonaResponse actualizar(Long idPersona, PersonaRequest r) {
    var p = personaRepo.findById(idPersona)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Persona no encontrada"));
    r.apply(p);
    try {
      p = personaRepo.save(p);
      return PersonaResponse.from(p);
    } catch (DataIntegrityViolationException e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "DNI ya existe", e);
    }
  }

  public PersonaResponse actualizarParcial(Long idPersona, PersonaRequest patch) {
    var p = personaRepo.findById(idPersona)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Persona no encontrada"));
    patch.applyNonNull(p);
    try {
      p = personaRepo.save(p);
      return PersonaResponse.from(p);
    } catch (DataIntegrityViolationException e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "DNI ya existe", e);
    }
  }
  @Override
  public void eliminar(Long idPersona) {
    if (!personaRepo.existsById(idPersona))
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Persona no encontrada");
    personaRepo.deleteById(idPersona);
  }
}
