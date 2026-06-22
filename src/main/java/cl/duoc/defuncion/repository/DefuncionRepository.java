package cl.duoc.defuncion.repository;

import cl.duoc.defuncion.model.Defuncion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefuncionRepository extends JpaRepository<Defuncion, Long> {

    Defuncion findByRutFallecido(String rutFallecido);
}
