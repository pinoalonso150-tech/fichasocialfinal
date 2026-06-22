package cl.duoc.defuncion.controller;

import cl.duoc.defuncion.dto.DefuncionDTO;
import cl.duoc.defuncion.model.Defuncion;
import cl.duoc.defuncion.service.DefuncionService;
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
@RequestMapping("/defunciones")
public class DefuncionController {

    private final DefuncionService defuncionService;

    public DefuncionController(DefuncionService defuncionService) {
        this.defuncionService = defuncionService;
    }

    // Endpoint para listar todas las defunciones
    @GetMapping
    public List<DefuncionDTO> listar() {
        return defuncionService.listar().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // Endpoint para obtener una defunción por rut del fallecido
    @GetMapping("/{rutFallecido}")
    public ResponseEntity<DefuncionDTO> buscarPorRutFallecido(@PathVariable String rutFallecido) {
        Defuncion defuncion = defuncionService.buscarPorRutFallecido(rutFallecido);
        if (defuncion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toDto(defuncion));
    }

    // Endpoint para crear una nueva defunción
    @PostMapping
    public ResponseEntity<DefuncionDTO> guardar(@Valid @RequestBody DefuncionDTO defuncionDTO) {
        Defuncion defuncion = toEntity(defuncionDTO);
        Defuncion defuncionGuardada = defuncionService.guardar(defuncion);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(defuncionGuardada));
    }

    // Endpoint para eliminar una defunción por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        defuncionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private DefuncionDTO toDto(Defuncion defuncion) {
        return DefuncionDTO.builder()
                .id(defuncion.getId())
                .rutFallecido(defuncion.getRutFallecido())
                .fechaDefuncion(defuncion.getFechaDefuncion())
                .causaMuerte(defuncion.getCausaMuerte())
                .lugarDefuncion(defuncion.getLugarDefuncion())
                .build();
    }

    private Defuncion toEntity(DefuncionDTO dto) {
        return Defuncion.builder()
                .id(dto.getId())
                .rutFallecido(dto.getRutFallecido())
                .fechaDefuncion(dto.getFechaDefuncion())
                .causaMuerte(dto.getCausaMuerte())
                .lugarDefuncion(dto.getLugarDefuncion())
                .build();
    }
}
