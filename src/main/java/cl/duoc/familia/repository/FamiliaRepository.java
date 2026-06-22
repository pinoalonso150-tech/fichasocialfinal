package cl.duoc.familia.repository;

import cl.duoc.familia.model.Familia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamiliaRepository extends JpaRepository<Familia, Long> {

    Familia findByRutFallecido(String rutFallecido);
}
