package cl.duoc.deudas.service;

import cl.duoc.deudas.model.Deuda;
import cl.duoc.deudas.repository.DeudaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DeudaService {

    private final DeudaRepository deudaRepository;

    public DeudaService(DeudaRepository deudaRepository) {
        this.deudaRepository = deudaRepository;
    }

    // Lista todas las deudas
    public List<Deuda> listar() {
        return deudaRepository.findAll();
    }

    // Busca deuda por rut del fallecido
    public Deuda buscarPorRutFallecido(String rutFallecido) {
        return deudaRepository.findByRutFallecido(rutFallecido);
    }

    // Guarda o actualiza una deuda
    public Deuda guardar(Deuda deuda) {
        return deudaRepository.save(deuda);
    }

    // Elimina una deuda por id
    public void eliminar(Long id) {
        deudaRepository.deleteById(id);
    }
}
