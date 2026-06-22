package cl.duoc.deudas.repository;

import cl.duoc.deudas.model.Deuda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeudaRepository extends JpaRepository<Deuda, Long> {

    Deuda findByRutFallecido(String rutFallecido);
}
