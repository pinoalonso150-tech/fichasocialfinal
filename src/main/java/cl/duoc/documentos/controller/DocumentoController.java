package cl.duoc.documentos.controller;

import cl.duoc.documentos.dto.DocumentoDTO;
import cl.duoc.documentos.service.DocumentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documentos")
@RequiredArgsConstructor
public class DocumentoController {

    private final DocumentoService documentoService;

    @GetMapping
    public ResponseEntity<List<DocumentoDTO>> getAll() {
        return ResponseEntity.ok(documentoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentoDTO> getById(@PathVariable Long id) {
        DocumentoDTO documento = documentoService.findById(id);
        return documento != null ? ResponseEntity.ok(documento) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<DocumentoDTO> create(@Valid @RequestBody DocumentoDTO dto) {
        DocumentoDTO saved = documentoService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentoDTO> update(@PathVariable Long id, @Valid @RequestBody DocumentoDTO dto) {
        dto.setId(id);
        DocumentoDTO updated = documentoService.save(dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        documentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
