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

import PComponent_Eva3.PComponent.model.Metodoenvio;
import PComponent_Eva3.PComponent.service.MetodoenvioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/metodoEnvio")
@Tag(name = "Metodo Envio Management System")
public class MetodoEnvioController {

    @Autowired
    private MetodoenvioService metodoenvioService;

    @GetMapping
    @Operation(summary = "View a list of available metodo envio")
    public ResponseEntity<List<Metodoenvio>> getAllMetodos() {
        List<Metodoenvio> metodoenvio = metodoenvioService.findAll();
        if (metodoenvio.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(metodoenvio);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a metodo envio by Id")
    public ResponseEntity<Metodoenvio> getMetodoById(@PathVariable Integer id) {
        Metodoenvio metodoenvio = metodoenvioService.findById(id);
        if (metodoenvio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(metodoenvio);
    }

    @PostMapping
    @Operation(summary = "Add a new metodo envio")
    public ResponseEntity<Metodoenvio> createMetodoEnvio(@RequestBody Metodoenvio metodoenvio) {
        Metodoenvio createdMetodoEnvio = metodoenvioService.save(metodoenvio);
        return ResponseEntity.status(201).body(createdMetodoEnvio);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing metodo envio")
    public ResponseEntity<Metodoenvio> updateMetodoEnvio(@PathVariable Integer id, @RequestBody Metodoenvio metodoenvio) {
        metodoenvio.setId(id);
        Metodoenvio updatedMetodoEnvio = metodoenvioService.save(metodoenvio);
        if (updatedMetodoEnvio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMetodoEnvio);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partial update an existing metodo envio")
    public ResponseEntity<Metodoenvio> updatePartialMetodoEnvio(@PathVariable Integer id, @RequestBody Metodoenvio metodoenvio) {
        metodoenvio.setId(id);
        Metodoenvio updatedMetodoEnvio = metodoenvioService.partialUpdate(metodoenvio);
        if (updatedMetodoEnvio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMetodoEnvio);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a metodo envio")
    public ResponseEntity<Void> deleteMetodoEnvio(@PathVariable Integer id) {
        metodoenvioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
