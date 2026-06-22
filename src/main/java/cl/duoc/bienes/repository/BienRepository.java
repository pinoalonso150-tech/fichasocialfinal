package cl.duoc.bienes.repository;

import cl.duoc.bienes.model.Bien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BienRepository extends JpaRepository<Bien, Long> {

    Bien findByRutFallecido(String rutFallecido);
}
