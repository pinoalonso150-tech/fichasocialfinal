package cl.duoc.bienes.service;

import cl.duoc.bienes.model.Bien;
import cl.duoc.bienes.repository.BienRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BienService {

    private final BienRepository bienRepository;

    public BienService(BienRepository bienRepository) {
        this.bienRepository = bienRepository;
    }

    // Lista todos los bienes
    public List<Bien> listar() {
        return bienRepository.findAll();
    }

    // Busca bienes por el rut del fallecido
    public Bien buscarPorRutFallecido(String rutFallecido) {
        return bienRepository.findByRutFallecido(rutFallecido);
    }

    // Guarda o actualiza un bien
    public Bien guardar(Bien bien) {
        return bienRepository.save(bien);
    }

    // Elimina un bien por id
    public void eliminar(Long id) {
        bienRepository.deleteById(id);
    }
}
