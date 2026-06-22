package cl.duoc.personas.controller;

import cl.duoc.personas.dto.PersonaDTO;
import cl.duoc.personas.model.Persona;
import cl.duoc.personas.service.PersonaService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    
    @GetMapping
    public List<PersonaDTO> listar() {
        return personaService.listar().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{rut}")
    public ResponseEntity<PersonaDTO> buscarPorRut(@PathVariable String rut) {
        Persona persona = personaService.buscarPorRut(rut);
        if (persona == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toDto(persona));
    }

    @PostMapping
    public ResponseEntity<PersonaDTO> guardar(@Valid @RequestBody PersonaDTO personaDTO) {
        Persona persona = toEntity(personaDTO);
        Persona personaGuardada = personaService.guardar(persona);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(personaGuardada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        personaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private PersonaDTO toDto(Persona persona) {
        return PersonaDTO.builder()
                .id(persona.getId())
                .rut(persona.getRut())
                .nombres(persona.getNombres())
                .apellidos(persona.getApellidos())
                .edad(persona.getEdad())
                .direccion(persona.getDireccion())
                .fechaNacimiento(persona.getFechaNacimiento())
                .build();
    }

    private Persona toEntity(PersonaDTO dto) {
        return Persona.builder()
                .id(dto.getId())
                .rut(dto.getRut())
                .nombres(dto.getNombres())
                .apellidos(dto.getApellidos())
                .edad(dto.getEdad())
                .direccion(dto.getDireccion())
                .fechaNacimiento(dto.getFechaNacimiento())
                .build();
    }
}
