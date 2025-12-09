package PComponent_Eva3.PComponent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PComponent_Eva3.PComponent.model.Venta;
import PComponent_Eva3.PComponent.repository.VentaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    
    public Venta save(Venta venta) {
        return ventaRepository.save(venta);
    } 

    public Venta findById(Integer id) {
        return ventaRepository.findById(id).orElse(null);
    }

    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }

    public Venta partialUpdate(Venta venta){
        Venta existingVenta = ventaRepository.findById(venta.getId()).orElse(null);
        if (existingVenta != null) {
            if (venta.getMetodoEnvio() != null) {
                existingVenta.setMetodoEnvio((venta.getMetodoEnvio()));
            }

            return ventaRepository.save(existingVenta);
        }
        return null;
    }

    public void deleteById(Integer id) {
        ventaRepository.deleteById(id);
    }
}

