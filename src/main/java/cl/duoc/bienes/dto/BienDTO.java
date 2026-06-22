package cl.duoc.bienes.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BienDTO {

    private Long id;

    @NotBlank(message = "El RUT del fallecido es obligatorio")
    private String rutFallecido;

    @NotBlank(message = "El tipo de bien es obligatorio")
    private String tipoBien;

    private String descripcion;

    @NotNull(message = "El valor estimado es obligatorio")
    @DecimalMin(value = "0.0", message = "El valor estimado debe ser mayor o igual a 0")
    private Double valorEstimado;
}
