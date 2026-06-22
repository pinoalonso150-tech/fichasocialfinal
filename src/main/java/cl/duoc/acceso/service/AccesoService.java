package cl.duoc.acceso.service;

import cl.duoc.acceso.model.Acceso;
import cl.duoc.acceso.repository.AccesoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AccesoService {

    private final AccesoRepository accesoRepository;

    public AccesoService(AccesoRepository accesoRepository) {
        this.accesoRepository = accesoRepository;
    }

    public List<Acceso> listar() {
        return accesoRepository.findAll();
    }

    public List<Acceso> buscarPorRutFuncionario(String rutFuncionario) {
        return accesoRepository.findByRutFuncionario(rutFuncionario);
    }

    public Acceso guardar(Acceso acceso) {
        return accesoRepository.save(acceso);
    }

    public void eliminar(Long id) {
        accesoRepository.deleteById(id);
    }
}
