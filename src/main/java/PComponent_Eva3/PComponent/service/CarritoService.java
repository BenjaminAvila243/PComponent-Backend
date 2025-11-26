package PComponent_Eva3.PComponent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PComponent_Eva3.PComponent.model.Carrito;
import PComponent_Eva3.PComponent.repository.CarritoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    public List<Carrito> findAll() {
        return carritoRepository.findAll();
    }

    public Carrito findById(Integer id) {
        return carritoRepository.findById(id).orElse(null);
    }

    public Carrito save(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    public Carrito partialUpdate(Carrito carrito) {
        Carrito existing = carritoRepository.findById(carrito.getId()).orElse(null);

        if (existing == null) {
            return null;
        }

        if (carrito.getNombreProducto() != null) {
            existing.setNombreProducto(carrito.getNombreProducto());
        }

        if (carrito.getCantidad() != null) {
            existing.setCantidad(carrito.getCantidad());
        }

        return carritoRepository.save(existing);
    }

    public void deleteById(Integer id) {
        carritoRepository.deleteById(id);
    }
}
