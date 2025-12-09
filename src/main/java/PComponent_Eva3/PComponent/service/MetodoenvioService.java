package PComponent_Eva3.PComponent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PComponent_Eva3.PComponent.model.Metodoenvio;
import PComponent_Eva3.PComponent.repository.MetodoEnvioRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class MetodoenvioService {

    @Autowired
    private MetodoEnvioRepository metodoEnvioRepository;

    public List<Metodoenvio> findAll() {
        List<Metodoenvio> metodoEnvios = metodoEnvioRepository.findAll();
        return metodoEnvios;
    }

    public Metodoenvio findById(Integer id) {
        Metodoenvio metodoEnvio = metodoEnvioRepository.findById(id).orElse(null);
        return metodoEnvio;
    }

    public Metodoenvio partialUpdate(Metodoenvio metodoEnvio) {
        Metodoenvio existingMetodoEnvio = metodoEnvioRepository.findById(metodoEnvio.getId()).orElse(null);
        if (existingMetodoEnvio != null) {
            if (metodoEnvio.getNombre() != null) {
                existingMetodoEnvio.setNombre(metodoEnvio.getNombre());
            }
            if (metodoEnvio.getTipoEnvio() != null) {
                existingMetodoEnvio.setTipoEnvio(metodoEnvio.getTipoEnvio());
            }
            if (metodoEnvio.getCosto() != null) {
                existingMetodoEnvio.setCosto(metodoEnvio.getCosto());
            }
            return metodoEnvioRepository.save(existingMetodoEnvio);
        }
        return null;
    }

    public Metodoenvio save(Metodoenvio metodoEnvio) {
        return metodoEnvioRepository.save(metodoEnvio);
    }

    public void deleteById(Integer id) {
        metodoEnvioRepository.deleteById(id);
    }
}
