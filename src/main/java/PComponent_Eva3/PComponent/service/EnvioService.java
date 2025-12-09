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

    @SuppressWarnings("null")
    public Envio findById(Integer id) {
        return envioRepository.findById(id).orElse(null);
    }

    @SuppressWarnings("null")
    public Envio save(Envio envio) {
        return envioRepository.save(envio);
    }

    public Envio partialUpdate(Envio envio) {
        Envio existingEnvio = envioRepository.findById(envio.getId()).orElse(null);
        if (existingEnvio != null) {
            return envioRepository.save(existingEnvio);
        }
        return null;
    }

    public void deleteById(Integer id) {
        envioRepository.deleteById(id);
    } 
    
}