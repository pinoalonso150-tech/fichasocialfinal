package cl.duoc.finanzas.repository;

import cl.duoc.finanzas.model.Finanza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanzaRepository extends JpaRepository<Finanza, Long> {

    Finanza findByRutFallecido(String rutFallecido);
}
