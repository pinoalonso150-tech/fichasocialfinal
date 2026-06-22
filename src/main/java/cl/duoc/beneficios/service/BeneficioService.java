package cl.duoc.beneficios.service;

import cl.duoc.beneficios.model.Beneficio;
import cl.duoc.beneficios.repository.BeneficioRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BeneficioService {

    private final BeneficioRepository beneficioRepository;

    public BeneficioService(BeneficioRepository beneficioRepository) {
        this.beneficioRepository = beneficioRepository;
    }

    // Lista todos los beneficios
    public List<Beneficio> listar() {
        return beneficioRepository.findAll();
    }

    // Busca beneficio por rut del fallecido
    public Beneficio buscarPorRutFallecido(String rutFallecido) {
        return beneficioRepository.findByRutFallecido(rutFallecido);
    }

    // Guarda o actualiza un beneficio
    public Beneficio guardar(Beneficio beneficio) {
        return beneficioRepository.save(beneficio);
    }

    // Elimina un beneficio por id
    public void eliminar(Long id) {
        beneficioRepository.deleteById(id);
    }
}
