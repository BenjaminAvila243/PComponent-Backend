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

import PComponent_Eva3.PComponent.model.Metodopago;
import PComponent_Eva3.PComponent.service.MetodopagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/metodoPago")
@Tag(name = "Metodo Pago Management System")
public class MetodoPagoController {

    @Autowired
    private MetodopagoService metodopagoService;

    @GetMapping
    @Operation(summary = "View a list of available metodo pago")
    public ResponseEntity<List<Metodopago>> getAllMetodos() {
        List<Metodopago> metodopagos = metodopagoService.findAll();
        if (metodopagos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(metodopagos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a metodo pago by Id")
    public ResponseEntity<Metodopago> getMetodoById(@PathVariable Integer id) {
        Metodopago metodopago = metodopagoService.findById(id);
        if (metodopago == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(metodopago);
    }

    @PostMapping
    @Operation(summary = "Add a new metodo pago")
    public ResponseEntity<Metodopago> createMetodoPago(@RequestBody Metodopago metodopago) {
        Metodopago createdMetodoPago = metodopagoService.save(metodopago);
        return ResponseEntity.status(201).body(createdMetodoPago);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing metodo pago")
    public ResponseEntity<Metodopago> updateMetodoPago(@PathVariable Integer id, @RequestBody Metodopago metodopago) {
        metodopago.setId(id);
        Metodopago updatedMetodoPago = metodopagoService.save(metodopago);
        if (updatedMetodoPago == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMetodoPago);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partial update an existing metodo pago")
    public ResponseEntity<Metodopago> updatePartialMetodoPago(@PathVariable Integer id, @RequestBody Metodopago metodopago) {
        metodopago.setId(id);
        Metodopago updatedMetodoPago = metodopagoService.partialUpdate(metodopago);
        if (updatedMetodoPago == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMetodoPago);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a metodo pago")
    public ResponseEntity<Void> deleteMetodoPago(@PathVariable Integer id) {
        metodopagoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
