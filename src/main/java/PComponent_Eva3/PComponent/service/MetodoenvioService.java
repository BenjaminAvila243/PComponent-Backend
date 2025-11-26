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
        return metodoEnvioRepository.findAll();
    }

    public Metodoenvio findById(Integer id) {
        Metodoenvio metodoenvio = metodoEnvioRepository.findById(id).orElse(null);
        return metodoenvio;
    }

    public Metodoenvio save(Metodoenvio metodoenvio) {
        return metodoEnvioRepository.save(metodoenvio);
    }

    public Metodoenvio partialUpdate(Metodoenvio metodoenvio){
        Metodoenvio existingMetodoenvio = metodoEnvioRepository.findById(metodoenvio.getId()).orElse(null);
        if (existingMetodoenvio != null) {
            if (metodoenvio.getNombre() != null) {
                existingMetodoenvio.setNombre(metodoenvio.getNombre());
            }

            if(metodoenvio.getTipoEnvio() != null) {
                existingMetodoenvio.setTipoEnvio(metodoenvio.getTipoEnvio());
            }

            if(metodoenvio.getCosto() != null) {
                existingMetodoenvio.setCosto(metodoenvio.getCosto());
            }

            if(metodoenvio.getTiempoEntrega() != null) {
                existingMetodoenvio.setTiempoEntrega(metodoenvio.getTiempoEntrega());
            }

            if(metodoenvio.getDireccion() != null) {
                existingMetodoenvio.setDireccion(metodoenvio.getDireccion());
            }

            if(metodoenvio.getTelefono() != null) {
                existingMetodoenvio.setTelefono(metodoenvio.getTelefono());
            }

            if(metodoenvio.getCorreo() != null) {
                existingMetodoenvio.setCorreo(metodoenvio.getCorreo());
            }

            if(metodoenvio.getIdVenta() != null) {
                existingMetodoenvio.setIdVenta(metodoenvio.getIdVenta());
            }

            return save(existingMetodoenvio);
        }
        return null;
    }

    public void deleteById(Integer id) {
        metodoEnvioRepository.deleteById(id);
    }
}
