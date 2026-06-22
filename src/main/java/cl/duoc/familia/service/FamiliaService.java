package cl.duoc.familia.service;

import cl.duoc.familia.model.Familia;
import cl.duoc.familia.repository.FamiliaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FamiliaService {

    private final FamiliaRepository familiaRepository;

    public FamiliaService(FamiliaRepository familiaRepository) {
        this.familiaRepository = familiaRepository;
    }

    // Lista todas las familias registradas
    public List<Familia> listar() {
        return familiaRepository.findAll();
    }

    // Busca un familiar por el rut del fallecido
    public Familia buscarPorRutFallecido(String rutFallecido) {
        return familiaRepository.findByRutFallecido(rutFallecido);
    }

    // Guarda o actualiza un familiar
    public Familia guardar(Familia familia) {
        return familiaRepository.save(familia);
    }

    // Elimina un familiar por id
    public void eliminar(Long id) {
        familiaRepository.deleteById(id);
    }
}
