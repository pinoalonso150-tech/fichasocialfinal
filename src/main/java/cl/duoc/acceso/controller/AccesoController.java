package cl.duoc.acceso.controller;

import cl.duoc.acceso.dto.AccesoDTO;
import cl.duoc.acceso.model.Acceso;
import cl.duoc.acceso.service.AccesoService;
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
@RequestMapping("/accesos")
public class AccesoController {

    private final AccesoService accesoService;

    public AccesoController(AccesoService accesoService) {
        this.accesoService = accesoService;
    }

    @GetMapping
    public List<AccesoDTO> listar() {
        return accesoService.listar().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{rutFuncionario}")
    public ResponseEntity<List<AccesoDTO>> buscarPorRutFuncionario(@PathVariable String rutFuncionario) {
        List<Acceso> accesos = accesoService.buscarPorRutFuncionario(rutFuncionario);
        if (accesos == null || accesos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(accesos.stream().map(this::toDto).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<AccesoDTO> guardar(@Valid @RequestBody AccesoDTO accesoDTO) {
        Acceso acceso = toEntity(accesoDTO);
        Acceso accesoGuardado = accesoService.guardar(acceso);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(accesoGuardado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        accesoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private AccesoDTO toDto(Acceso acceso) {
        return AccesoDTO.builder()
                .id(acceso.getId())
                .rutFuncionario(acceso.getRutFuncionario())
                .nombreFuncionario(acceso.getNombreFuncionario())
                .cargo(acceso.getCargo())
                .fechaAcceso(acceso.getFechaAcceso())
                .observacion(acceso.getObservacion())
                .build();
    }

    private Acceso toEntity(AccesoDTO dto) {
        return Acceso.builder()
                .id(dto.getId())
                .rutFuncionario(dto.getRutFuncionario())
                .nombreFuncionario(dto.getNombreFuncionario())
                .cargo(dto.getCargo())
                .fechaAcceso(dto.getFechaAcceso())
                .observacion(dto.getObservacion())
                .build();
    }
}
