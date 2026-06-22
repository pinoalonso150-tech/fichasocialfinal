package cl.duoc.bienes.controller;

import cl.duoc.bienes.dto.BienDTO;
import cl.duoc.bienes.model.Bien;
import cl.duoc.bienes.service.BienService;
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
@RequestMapping("/bienes")
public class BienController {

    private final BienService bienService;

    public BienController(BienService bienService) {
        this.bienService = bienService;
    }

    // Endpoint para listar todos los bienes
    @GetMapping
    public List<BienDTO> listar() {
        return bienService.listar().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // Endpoint para obtener un bien por rut del fallecido
    @GetMapping("/{rutFallecido}")
    public ResponseEntity<BienDTO> buscarPorRutFallecido(@PathVariable String rutFallecido) {
        Bien bien = bienService.buscarPorRutFallecido(rutFallecido);
        if (bien == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toDto(bien));
    }

    // Endpoint para crear un nuevo bien
    @PostMapping
    public ResponseEntity<BienDTO> guardar(@Valid @RequestBody BienDTO bienDTO) {
        Bien bien = toEntity(bienDTO);
        Bien bienGuardado = bienService.guardar(bien);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(bienGuardado));
    }

    // Endpoint para eliminar un bien por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        bienService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private BienDTO toDto(Bien bien) {
        return BienDTO.builder()
                .id(bien.getId())
                .rutFallecido(bien.getRutFallecido())
                .tipoBien(bien.getTipoBien())
                .descripcion(bien.getDescripcion())
                .valorEstimado(bien.getValorEstimado())
                .build();
    }

    private Bien toEntity(BienDTO dto) {
        return Bien.builder()
                .id(dto.getId())
                .rutFallecido(dto.getRutFallecido())
                .tipoBien(dto.getTipoBien())
                .descripcion(dto.getDescripcion())
                .valorEstimado(dto.getValorEstimado())
                .build();
    }
}
// Commit 5 - Ajustes microservicio Bienes