package PComponent_Eva3.PComponent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PComponent_Eva3.PComponent.model.Categoria;
import PComponent_Eva3.PComponent.repository.CategoriaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria findById(Integer id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria partialUpdate(Categoria categoria) {
        Categoria existingCategoria = categoriaRepository.findById(categoria.getId()).orElse(null);

        if (existingCategoria != null) {

            if (categoria.getNombreCategoria() != null) {
                existingCategoria.setNombreCategoria(categoria.getNombreCategoria());
            }

            if (categoria.getDescripcion() != null) {
                existingCategoria.setDescripcion(categoria.getDescripcion());
            }

            if (categoria.getCategoriaPadre() != null) {
                existingCategoria.setCategoriaPadre(categoria.getCategoriaPadre());
            }

            return categoriaRepository.save(existingCategoria);
        }

        return null;
    }

    public void deleteById(Integer id) {
        categoriaRepository.deleteById(id);
    }
}

