package cl.duoc.beneficios.controller;

import cl.duoc.beneficios.dto.BeneficioDTO;
import cl.duoc.beneficios.model.Beneficio;
import cl.duoc.beneficios.service.BeneficioService;
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
@RequestMapping("/beneficios")
public class BeneficioController {

    private final BeneficioService beneficioService;

    public BeneficioController(BeneficioService beneficioService) {
        this.beneficioService = beneficioService;
    }

    // Endpoint para listar todos los beneficios
    @GetMapping
    public List<BeneficioDTO> listar() {
        return beneficioService.listar().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // Endpoint para obtener un beneficio por rut del fallecido
    @GetMapping("/{rutFallecido}")
    public ResponseEntity<BeneficioDTO> buscarPorRutFallecido(@PathVariable String rutFallecido) {
        Beneficio beneficio = beneficioService.buscarPorRutFallecido(rutFallecido);
        if (beneficio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toDto(beneficio));
    }

    // Endpoint para crear un nuevo beneficio
    @PostMapping
    public ResponseEntity<BeneficioDTO> guardar(@Valid @RequestBody BeneficioDTO beneficioDTO) {
        Beneficio beneficio = toEntity(beneficioDTO);
        Beneficio beneficioGuardado = beneficioService.guardar(beneficio);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(beneficioGuardado));
    }

    // Endpoint para eliminar un beneficio por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        beneficioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private BeneficioDTO toDto(Beneficio beneficio) {
        return BeneficioDTO.builder()
                .id(beneficio.getId())
                .rutFallecido(beneficio.getRutFallecido())
                .tipoBeneficio(beneficio.getTipoBeneficio())
                .descripcion(beneficio.getDescripcion())
                .monto(beneficio.getMonto())
                .build();
    }

    private Beneficio toEntity(BeneficioDTO dto) {
        return Beneficio.builder()
                .id(dto.getId())
                .rutFallecido(dto.getRutFallecido())
                .tipoBeneficio(dto.getTipoBeneficio())
                .descripcion(dto.getDescripcion())
                .monto(dto.getMonto())
                .build();
    }
}
// Commit 4 - Ajustes microservicio Beneficios