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


    @SuppressWarnings("null")
    public Metodopago save(Metodopago metodoPago) {
        return metodoPagoRepository.save(metodoPago);
    } 

    @SuppressWarnings("null")
    public Metodopago findById(Integer id) {
        return metodoPagoRepository.findById(id).orElse(null);
    }

    @SuppressWarnings("null")
    public List<Metodopago> findAll() {
        return metodoPagoRepository.findAll();
    }


    public Metodopago updateMetodoPagoRepository(Metodopago metodoPago) {
        return save(metodoPago);
    }


    public void deleteById(Integer id) {
        metodoPagoRepository.deleteById(id);
    }


}
