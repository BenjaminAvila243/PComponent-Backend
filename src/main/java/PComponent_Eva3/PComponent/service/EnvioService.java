package PComponent_Eva3.PComponent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PComponent_Eva3.PComponent.model.Envio;
import PComponent_Eva3.PComponent.repository.EnvioRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    public List<Envio> findAll() {
        return envioRepository.findAll();
    }

    public Envio findById(Integer id) {
        return envioRepository.findById(id).orElse(null);
    }

    public Envio save(Envio envio) {
        return envioRepository.save(envio);
    }

    public void deleteById(Integer id) {
        envioRepository.deleteById(id);
    }

    public Envio partialUpdate(Envio envioData) {
        Envio existing = envioRepository.findById(envioData.getId()).orElse(null);
        if (existing == null) {
            return null;
        }

        if (envioData.getTipoEnvio() != null)
            existing.setTipoEnvio(envioData.getTipoEnvio());

        if (envioData.getCosto() != null)
            existing.setCosto(envioData.getCosto());

        if (envioData.getTiempoEntrega() != null)
            existing.setTiempoEntrega(envioData.getTiempoEntrega());

        if (envioData.getDireccion() != null)
            existing.setDireccion(envioData.getDireccion());

        if (envioData.getTelefono() != null)
            existing.setTelefono(envioData.getTelefono());

        if (envioData.getCorreo() != null)
            existing.setCorreo(envioData.getCorreo());

        if (envioData.getIdVenta() != null)
            existing.setIdVenta(envioData.getIdVenta());

        if (envioData.getNombre() != null)
            existing.setNombre(envioData.getNombre());

        return envioRepository.save(existing);
    }
}
