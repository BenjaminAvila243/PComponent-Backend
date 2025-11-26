package PComponent_Eva3.PComponent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PComponent_Eva3.PComponent.model.Metodopago;
import PComponent_Eva3.PComponent.repository.MetodopagoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class MetodopagoService {

    @Autowired
    private MetodopagoRepository metodoPagoRepository;

    public List<Metodopago> findAll() {
        return metodoPagoRepository.findAll();
    }

    public Metodopago findById(Integer id) {
        Metodopago metodopago = metodoPagoRepository.findById(id).orElse(null);
        return metodopago;
    }

    public Metodopago save(Metodopago metodopago) {
        return metodoPagoRepository.save(metodopago);
    }

    public Metodopago partialUpdate(Metodopago metodopago) {
        Metodopago existingMetodopago = metodoPagoRepository.findById(metodopago.getId()).orElse(null);
        
        if (existingMetodopago != null) {

            if (metodopago.getNombreMetodo() != null) {
                existingMetodopago.setNombreMetodo(metodopago.getNombreMetodo());
            }

            return metodoPagoRepository.save(existingMetodopago);
        }
        return null;
    }

    public void deleteById(Integer id) {
        metodoPagoRepository.deleteById(id);
    }
}
