package cl.duoc.defuncion.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DefuncionDTO {

    private Long id;

    @NotBlank(message = "El RUT del fallecido es obligatorio")
    private String rutFallecido;

    @NotBlank(message = "La fecha de defunción es obligatoria")
    private String fechaDefuncion;

    @NotBlank(message = "La causa de muerte es obligatoria")
    private String causaMuerte;

    @NotBlank(message = "El lugar de defunción es obligatorio")
    private String lugarDefuncion;
}
