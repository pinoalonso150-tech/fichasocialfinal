package cl.duoc.acceso.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccesoDTO {

    private Long id;

    @NotBlank(message = "El RUT del funcionario es obligatorio")
    private String rutFuncionario;

    @NotBlank(message = "El nombre del funcionario es obligatorio")
    private String nombreFuncionario;

    @NotBlank(message = "El cargo es obligatorio")
    private String cargo;

    @NotBlank(message = "La fecha de acceso es obligatoria")
    private String fechaAcceso;

    private String observacion;
}
