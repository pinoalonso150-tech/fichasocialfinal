package cl.duoc.documentos.service;

import cl.duoc.documentos.dto.DocumentoDTO;
import cl.duoc.documentos.model.Documento;
import cl.duoc.documentos.repository.DocumentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentoService {

    private final DocumentoRepository documentoRepository;

    public List<DocumentoDTO> findAll() {
        return documentoRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public DocumentoDTO findById(Long id) {
        return documentoRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    public DocumentoDTO save(DocumentoDTO dto) {
        Documento documento = toEntity(dto);
        return toDto(documentoRepository.save(documento));
    }

    public void deleteById(Long id) {
        documentoRepository.deleteById(id);
    }

    private DocumentoDTO toDto(Documento documento) {
        return DocumentoDTO.builder()
                .id(documento.getId())
                .rutFallecido(documento.getRutFallecido())
                .tipoDocumento(documento.getTipoDocumento())
                .numeroDocumento(documento.getNumeroDocumento())
                .observacion(documento.getObservacion())
                .build();
    }

    private Documento toEntity(DocumentoDTO dto) {
        return Documento.builder()
                .id(dto.getId())
                .rutFallecido(dto.getRutFallecido())
                .tipoDocumento(dto.getTipoDocumento())
                .numeroDocumento(dto.getNumeroDocumento())
                .observacion(dto.getObservacion())
                .build();
    }
}
