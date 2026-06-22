package cl.duoc.beneficios.repository;

import cl.duoc.beneficios.model.Beneficio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficioRepository extends JpaRepository<Beneficio, Long> {

    Beneficio findByRutFallecido(String rutFallecido);
}
