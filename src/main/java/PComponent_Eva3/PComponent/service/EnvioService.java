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
    private EnvioRepository comunaRepository;

    public List<Envio> findAll() {
        return comunaRepository.findAll();
    }

    @SuppressWarnings("null")
    public Envio findById(Integer id) {
        return comunaRepository.findById(id).orElse(null);
    }

    @SuppressWarnings("null")
    public Envio save(Envio comuna) {
        return comunaRepository.save(comuna);
    } 
    
}