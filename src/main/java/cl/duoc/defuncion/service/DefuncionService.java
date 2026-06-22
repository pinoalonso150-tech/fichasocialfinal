package cl.duoc.defuncion.service;

import cl.duoc.defuncion.model.Defuncion;
import cl.duoc.defuncion.repository.DefuncionRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DefuncionService {

    private final DefuncionRepository defuncionRepository;

    public DefuncionService(DefuncionRepository defuncionRepository) {
        this.defuncionRepository = defuncionRepository;
    }

    // Lista todas las defunciones
    public List<Defuncion> listar() {
        return defuncionRepository.findAll();
    }

    // Busca una defunción por rut del fallecido
    public Defuncion buscarPorRutFallecido(String rutFallecido) {
        return defuncionRepository.findByRutFallecido(rutFallecido);
    }

    // Guarda o actualiza una defunción
    public Defuncion guardar(Defuncion defuncion) {
        return defuncionRepository.save(defuncion);
    }

    // Elimina una defunción por id
    public void eliminar(Long id) {
        defuncionRepository.deleteById(id);
    }
}
