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

import PComponent_Eva3.PComponent.service.EnvioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import PComponent_Eva3.PComponent.model.Envio;

@RestController
@RequestMapping("/api/envio")
@Tag(name = "Envio Management System")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    @Operation(summary = "View a list of available envios")
    public ResponseEntity<List<Envio>> getAllEnvios() {
        List<Envio> envios = envioService.findAll();
        if (envios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(envios);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a envio by Id")
    public ResponseEntity<Envio> getEnvioById(@PathVariable Integer id) {
        Envio envio = envioService.findById(id);
        if (envio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(envio);
    }

    @PostMapping
    @Operation(summary = "Add a new envio")
    public ResponseEntity<Envio> createEnvio(@RequestBody Envio envio) {
        Envio createdEnvio = envioService.save(envio);
        return ResponseEntity.status(201).body(createdEnvio);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing envio")
    public ResponseEntity<Envio> updateEnvio(@PathVariable Integer id, @RequestBody Envio envio) {
        envio.setId(id);
        Envio updatedEnvio = envioService.save(envio);
        if (updatedEnvio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEnvio);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partial update an existing envio")
    public ResponseEntity<Envio> updatePartialEnvio(@PathVariable Integer id, @RequestBody Envio envio) {
        envio.setId(id);
        Envio updatedEnvio = envioService.partialUpdate(envio);
        if (updatedEnvio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEnvio);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a envio")
    public ResponseEntity<Void> deleteEnvio(@PathVariable Integer id) {
        envioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
