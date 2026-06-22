package cl.duoc.deudas.controller;

import cl.duoc.deudas.dto.DeudaDTO;
import cl.duoc.deudas.model.Deuda;
import cl.duoc.deudas.service.DeudaService;
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
@RequestMapping("/deudas")
public class DeudaController {

    private final DeudaService deudaService;

    public DeudaController(DeudaService deudaService) {
        this.deudaService = deudaService;
    }

    // Endpoint para listar todas las deudas
    @GetMapping
    public List<DeudaDTO> listar() {
        return deudaService.listar().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // Endpoint para obtener deuda por rut del fallecido
    @GetMapping("/{rutFallecido}")
    public ResponseEntity<DeudaDTO> buscarPorRutFallecido(@PathVariable String rutFallecido) {
        Deuda deuda = deudaService.buscarPorRutFallecido(rutFallecido);
        if (deuda == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toDto(deuda));
    }

    // Endpoint para crear una nueva deuda
    @PostMapping
    public ResponseEntity<DeudaDTO> guardar(@Valid @RequestBody DeudaDTO deudaDTO) {
        Deuda deuda = toEntity(deudaDTO);
        Deuda deudaGuardada = deudaService.guardar(deuda);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(deudaGuardada));
    }

    // Endpoint para eliminar una deuda por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        deudaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private DeudaDTO toDto(Deuda deuda) {
        return DeudaDTO.builder()
                .id(deuda.getId())
                .rutFallecido(deuda.getRutFallecido())
                .entidad(deuda.getEntidad())
                .montoDeuda(deuda.getMontoDeuda())
                .estado(deuda.getEstado())
                .build();
    }

    private Deuda toEntity(DeudaDTO dto) {
        return Deuda.builder()
                .id(dto.getId())
                .rutFallecido(dto.getRutFallecido())
                .entidad(dto.getEntidad())
                .montoDeuda(dto.getMontoDeuda())
                .estado(dto.getEstado())
                .build();
    }
}
// commit 7 ajustes deudas