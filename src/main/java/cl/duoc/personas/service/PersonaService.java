package cl.duoc.personas.service;

import cl.duoc.personas.model.Persona;
import cl.duoc.personas.repository.PersonaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    // Lista todas las personas registradas
    public List<Persona> listar() {
        return personaRepository.findAll();
    }

    // Busca una persona por su rut
    public Persona buscarPorRut(String rut) {
        return personaRepository.findByRut(rut);
    }

    // Guarda o actualiza una persona
    public Persona guardar(Persona persona) {
        return personaRepository.save(persona);
    }

    // Elimina una persona por id
    public void eliminar(Long id) {
        personaRepository.deleteById(id);
    }
}
