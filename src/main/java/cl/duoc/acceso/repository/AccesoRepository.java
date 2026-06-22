package cl.duoc.acceso.repository;

import cl.duoc.acceso.model.Acceso;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccesoRepository extends JpaRepository<Acceso, Long> {

    List<Acceso> findByRutFuncionario(String rutFuncionario);
}
