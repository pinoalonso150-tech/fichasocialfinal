package cl.duoc.finanzas.controller;

import cl.duoc.finanzas.dto.FinanzaDTO;
import cl.duoc.finanzas.model.Finanza;
import cl.duoc.finanzas.service.FinanzaService;
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
@RequestMapping("/finanzas")
public class FinanzaController {

    private final FinanzaService finanzaService;

    public FinanzaController(FinanzaService finanzaService) {
        this.finanzaService = finanzaService;
    }

    // Endpoint para listar todas las finanzas
    @GetMapping
    public List<FinanzaDTO> listar() {
        return finanzaService.listar().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // Endpoint para obtener finanza por rut del fallecido
    @GetMapping("/{rutFallecido}")
    public ResponseEntity<FinanzaDTO> buscarPorRutFallecido(@PathVariable String rutFallecido) {
        Finanza finanza = finanzaService.buscarPorRutFallecido(rutFallecido);
        if (finanza == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toDto(finanza));
    }

    // Endpoint para crear una nueva finanza
    @PostMapping
    public ResponseEntity<FinanzaDTO> guardar(@Valid @RequestBody FinanzaDTO finanzaDTO) {
        Finanza finanza = toEntity(finanzaDTO);
        Finanza finanzaGuardada = finanzaService.guardar(finanza);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(finanzaGuardada));
    }

    // Endpoint para eliminar una finanza por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        finanzaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private FinanzaDTO toDto(Finanza finanza) {
        return FinanzaDTO.builder()
                .id(finanza.getId())
                .rutFallecido(finanza.getRutFallecido())
                .ingresos(finanza.getIngresos())
                .gastos(finanza.getGastos())
                .saldoDisponible(finanza.getSaldoDisponible())
                .build();
    }

    private Finanza toEntity(FinanzaDTO dto) {
        return Finanza.builder()
                .id(dto.getId())
                .rutFallecido(dto.getRutFallecido())
                .ingresos(dto.getIngresos())
                .gastos(dto.getGastos())
                .saldoDisponible(dto.getSaldoDisponible())
                .build();
    }
}
