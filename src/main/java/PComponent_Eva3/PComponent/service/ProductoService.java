package PComponent_Eva3.PComponent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import PComponent_Eva3.PComponent.model.Metodoenvio;
import PComponent_Eva3.PComponent.model.Producto;
import PComponent_Eva3.PComponent.repository.MetodoEnvioRepository;
import PComponent_Eva3.PComponent.repository.ProductoRepository;

public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll() {
        List<Producto> Producto = productoRepository.findAll();
        return Producto;
    }

    @SuppressWarnings("null")
    public Producto findById(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto partialUpdate(Producto producto) {
        Producto existingProducto = productoRepository.findById(producto.getId()).orElse(null);

        if (existingProducto != null) {

            if (producto.getNombre() != null) {
                existingProducto.setNombre(producto.getNombre());
            }

            if (producto.getPrecio() != null) {
                existingProducto.setPrecio(producto.getPrecio());
            }
            
            if (producto.getImagenUrl() != null) {
                existingProducto.setImagenUrl(producto.getImagenUrl());
            }
            if (producto.getDescripcion() != null) {
                existingProducto.setDescripcion(producto.getDescripcion());
            }
            if (producto.getStock() != null) {
                existingProducto.setStock(producto.getStock());
            }

            if (producto.getMarca() != null) {
                existingProducto.setMarca(producto.getMarca());
            }

            return productoRepository.save(existingProducto);
        }

        return null;
    }



    public void deleteById(Integer id) {
        productoRepository.deleteById(id);;
    }

}
