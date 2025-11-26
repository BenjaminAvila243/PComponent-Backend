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

import PComponent_Eva3.PComponent.model.Marca;
import PComponent_Eva3.PComponent.service.MarcaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/marca")
@Tag(name = "Marca Management System")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping
    @Operation(summary = "View a list of available marcas")
    public ResponseEntity<List<Marca>> getAllMarcas() {
        List<Marca> marca = marcaService.findAll();
        if (marca.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(marca);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a marca by Id")
    public ResponseEntity<Marca> getMarcaById(@PathVariable Integer id) {
        Marca marca = marcaService.findById(id);
        if (marca == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(marca);
    }

    @PostMapping
    @Operation(summary = "Add a new marca")
    public ResponseEntity<Marca> createMarca(@RequestBody Marca marca) {
        Marca createdMarca = marcaService.save(marca);
        return ResponseEntity.status(201).body(createdMarca);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing marca")
    public ResponseEntity<Marca> updateMarca(@PathVariable Integer id, @RequestBody Marca marca) {
        marca.setId(id);
        Marca updatedMarca = marcaService.save(marca);
        if (updatedMarca == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMarca);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partial update an existing marca")
    public ResponseEntity<Marca> updatePartialMarca(@PathVariable Integer id, @RequestBody Marca marca) {
        marca.setId(id);
        Marca updatedMarca = marcaService.partialUpdate(marca);
        if (updatedMarca == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMarca);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a marca")
    public ResponseEntity<Void> deleteMarca(@PathVariable Integer id) {
        marcaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
