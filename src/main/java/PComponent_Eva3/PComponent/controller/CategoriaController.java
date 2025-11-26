package PComponent_Eva3.PComponent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import PComponent_Eva3.PComponent.model.Categoria;
import PComponent_Eva3.PComponent.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/categoria")
@Tag(name = "Categoria Management System")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    @Operation(summary = "View a list of available categorias")
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        List<Categoria> categorias = categoriaService.findAll();
        if (categorias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a categoria by Id")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Integer id) {
        Categoria categoria = categoriaService.findById(id);
        if (categoria == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    @Operation(summary = "Add a new categoria")
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) {
        Categoria createdCategoria = categoriaService.save(categoria);
        return ResponseEntity.status(201).body(createdCategoria);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing categoria")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {
        categoria.setId(id);
        Categoria updatedCategoria = categoriaService.save(categoria);
        if (updatedCategoria == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCategoria);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partial update an existing categoria")
    public ResponseEntity<Categoria> updatePartialCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {
        categoria.setId(id);
        Categoria updatedCategoria = categoriaService.partialUpdate(categoria);
        if (updatedCategoria == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCategoria);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a categoria")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Integer id) {
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
