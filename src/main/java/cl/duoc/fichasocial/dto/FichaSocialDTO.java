package cl.duoc.fichasocial.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FichaSocialDTO {

    private String rutFallecido;
    private Object persona;
    private Object defuncion;
    private Object familia;
    private Object bienes;
    private Object finanzas;
    private Object deudas;
    private Object beneficios;
    private Object documentos;
}
