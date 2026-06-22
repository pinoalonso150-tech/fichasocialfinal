package cl.duoc.defuncion.model;

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
@Table(name = "defunciones")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Defuncion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
