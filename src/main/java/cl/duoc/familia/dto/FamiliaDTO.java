package cl.duoc.familia.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FamiliaDTO {

    private Long id;

    @NotBlank(message = "El RUT del fallecido es obligatorio")
    private String rutFallecido;

    @NotBlank(message = "El nombre del familiar es obligatorio")
    private String nombreFamiliar;

    @NotBlank(message = "El parentesco es obligatorio")
    private String parentesco;

    @NotBlank(message = "El teléfono es obligatorio")
    private String telefono;
}
