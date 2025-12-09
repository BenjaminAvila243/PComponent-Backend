package PComponent_Eva3.PComponent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PComponent_Eva3.PComponent.model.Marca;
import PComponent_Eva3.PComponent.repository.MarcaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class MarcaService {
    @Autowired
    private MarcaRepository marcaRepository;

    public List<Marca> findAll() {
        return marcaRepository.findAll();
    }

    public Marca findById(Integer id) {
        Marca Marca = marcaRepository.findById(id).orElse(null);
        return Marca;
    }

    public Marca save(Marca Marca) {
        return marcaRepository.save(Marca);
    }

    public Marca partialUpdate(Marca Marca){
        Marca existingMarca = marcaRepository.findById(Marca.getId()).orElse(null);
        if (existingMarca != null) {
            if (Marca.getNombreMarca() != null) {
                existingMarca.setNombreMarca(Marca.getNombreMarca());
            }

            return marcaRepository.save(existingMarca);
        }
        return null;
    }

    public void deleteById(Integer id) {
        marcaRepository.deleteById(id);
    }

}