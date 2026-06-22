package cl.duoc.fichasocial.service;

import cl.duoc.beneficios.service.BeneficioService;
import cl.duoc.bienes.service.BienService;
import cl.duoc.defuncion.service.DefuncionService;
import cl.duoc.deudas.service.DeudaService;
import cl.duoc.documentos.dto.DocumentoDTO;
import cl.duoc.documentos.service.DocumentoService;
import cl.duoc.finanzas.service.FinanzaService;
import cl.duoc.familia.service.FamiliaService;
import cl.duoc.personas.service.PersonaService;
import cl.duoc.fichasocial.dto.FichaSocialDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class FichaSocialService {

    private final PersonaService personaService;
    private final DefuncionService defuncionService;
    private final FamiliaService familiaService;
    private final BienService bienService;
    private final FinanzaService finanzaService;
    private final DeudaService deudaService;
    private final BeneficioService beneficioService;
    private final DocumentoService documentoService;

    public FichaSocialService(
            PersonaService personaService,
            DefuncionService defuncionService,
            FamiliaService familiaService,
            BienService bienService,
            FinanzaService finanzaService,
            DeudaService deudaService,
            BeneficioService beneficioService,
            DocumentoService documentoService) {
        this.personaService = personaService;
        this.defuncionService = defuncionService;
        this.familiaService = familiaService;
        this.bienService = bienService;
        this.finanzaService = finanzaService;
        this.deudaService = deudaService;
        this.beneficioService = beneficioService;
        this.documentoService = documentoService;
    }

    public FichaSocialDTO obtenerFichaCompleta(String rut) {
        Object persona = personaService.buscarPorRut(rut);
        if (persona == null) {
            return null;
        }

        List<DocumentoDTO> documentosPorRut = documentoService.findAll().stream()
                .filter(documento -> rut.equals(documento.getRutFallecido()))
                .collect(Collectors.toList());

        return FichaSocialDTO.builder()
                .rutFallecido(rut)
                .persona(persona)
                .defuncion(defuncionService.buscarPorRutFallecido(rut))
                .familia(familiaService.buscarPorRutFallecido(rut))
                .bienes(bienService.buscarPorRutFallecido(rut))
                .finanzas(finanzaService.buscarPorRutFallecido(rut))
                .deudas(deudaService.buscarPorRutFallecido(rut))
                .beneficios(beneficioService.buscarPorRutFallecido(rut))
                .documentos(documentosPorRut)
                .build();
    }
}
