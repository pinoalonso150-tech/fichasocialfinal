package cl.duoc.acceso.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accesos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Acceso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
