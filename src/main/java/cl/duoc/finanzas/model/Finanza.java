package cl.duoc.finanzas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "finanzas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Finanza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El RUT del fallecido es obligatorio")
    private String rutFallecido;

    @NotNull(message = "Los ingresos son obligatorios")
    @DecimalMin(value = "0.0", message = "Los ingresos deben ser mayor o igual a 0")
    private Double ingresos;

    @NotNull(message = "Los gastos son obligatorios")
    @DecimalMin(value = "0.0", message = "Los gastos deben ser mayor o igual a 0")
    private Double gastos;

    @NotNull(message = "El saldo disponible es obligatorio")
    private Double saldoDisponible;
}
