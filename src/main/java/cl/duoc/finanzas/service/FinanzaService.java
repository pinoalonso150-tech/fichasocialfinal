package cl.duoc.finanzas.service;

import cl.duoc.finanzas.model.Finanza;
import cl.duoc.finanzas.repository.FinanzaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FinanzaService {

    private final FinanzaRepository finanzaRepository;

    public FinanzaService(FinanzaRepository finanzaRepository) {
        this.finanzaRepository = finanzaRepository;
    }

    // Lista todas las finanzas
    public List<Finanza> listar() {
        return finanzaRepository.findAll();
    }

    // Busca finanza por rut del fallecido
    public Finanza buscarPorRutFallecido(String rutFallecido) {
        return finanzaRepository.findByRutFallecido(rutFallecido);
    }

    // Guarda o actualiza una finanza
    public Finanza guardar(Finanza finanza) {
        return finanzaRepository.save(finanza);
    }

    // Elimina una finanza por id
    public void eliminar(Long id) {
        finanzaRepository.deleteById(id);
    }
}
