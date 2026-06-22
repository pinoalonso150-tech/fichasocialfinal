package cl.duoc.familia.controller;

import cl.duoc.familia.dto.FamiliaDTO;
import cl.duoc.familia.model.Familia;
import cl.duoc.familia.service.FamiliaService;
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
@RequestMapping("/familias")
public class FamiliaController {

    private final FamiliaService familiaService;

    public FamiliaController(FamiliaService familiaService) {
        this.familiaService = familiaService;
    }

    // Endpoint para listar todas las familias
    @GetMapping
    public List<FamiliaDTO> listar() {
        return familiaService.listar().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // Endpoint para obtener un familiar por rut del fallecido
    @GetMapping("/{rutFallecido}")
    public ResponseEntity<FamiliaDTO> buscarPorRutFallecido(@PathVariable String rutFallecido) {
        Familia familia = familiaService.buscarPorRutFallecido(rutFallecido);
        if (familia == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toDto(familia));
    }

    // Endpoint para crear un nuevo familiar
    @PostMapping
    public ResponseEntity<FamiliaDTO> guardar(@Valid @RequestBody FamiliaDTO familiaDTO) {
        Familia familia = toEntity(familiaDTO);
        Familia familiaGuardada = familiaService.guardar(familia);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(familiaGuardada));
    }

    // Endpoint para eliminar un familiar por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        familiaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private FamiliaDTO toDto(Familia familia) {
        return FamiliaDTO.builder()
                .id(familia.getId())
                .rutFallecido(familia.getRutFallecido())
                .nombreFamiliar(familia.getNombreFamiliar())
                .parentesco(familia.getParentesco())
                .telefono(familia.getTelefono())
                .build();
    }

    private Familia toEntity(FamiliaDTO dto) {
        return Familia.builder()
                .id(dto.getId())
                .rutFallecido(dto.getRutFallecido())
                .nombreFamiliar(dto.getNombreFamiliar())
                .parentesco(dto.getParentesco())
                .telefono(dto.getTelefono())
                .build();
    }
}
//commit 9 ajuste familia 