package PComponent_Eva3.PComponent.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PComponent_Eva3.PComponent.model.Comuna;
import PComponent_Eva3.PComponent.repository.ComunaRepository;

@Service
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    public List<Comuna> findAll() {
        return comunaRepository.findAll();
    }

    public Optional<Comuna> findById(Integer id) {
        return comunaRepository.findById(id);
    }

    public Comuna save(Comuna comuna) {
        return comunaRepository.save(comuna);
    }

    public Comuna partialUpdate(Integer id, Comuna comunaData) {
        return comunaRepository.findById(id).map(comuna -> {

            if (comunaData.getNombreComuna() != null) {
                comuna.setNombreComuna(comunaData.getNombreComuna());
            }

            if (comunaData.getRegion() != null) {
                comuna.setRegion(comunaData.getRegion());
            }

            return comunaRepository.save(comuna);

        }).orElse(null);
    }

    public void deleteById(Integer id) {
        comunaRepository.deleteById(id);
    }
}