package cl.duoc.beneficios.dto;

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
public class BeneficioDTO {

    private Long id;

    @NotBlank(message = "El RUT del fallecido es obligatorio")
    private String rutFallecido;

    @NotBlank(message = "El tipo de beneficio es obligatorio")
    private String tipoBeneficio;

    private String descripcion;

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.0", message = "El monto debe ser mayor o igual a 0")
    private Double monto;
}
