package cl.duoc.deudas.dto;

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
public class DeudaDTO {

    private Long id;

    @NotBlank(message = "El RUT del fallecido es obligatorio")
    private String rutFallecido;

    @NotBlank(message = "La entidad acreedora es obligatoria")
    private String entidad;

    @NotNull(message = "El monto de la deuda es obligatorio")
    @DecimalMin(value = "0.0", message = "El monto debe ser mayor o igual a 0")
    private Double montoDeuda;

    @NotBlank(message = "El estado de la deuda es obligatorio")
    private String estado;
}
