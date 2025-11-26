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

import PComponent_Eva3.PComponent.model.Venta;
import PComponent_Eva3.PComponent.service.VentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/venta")
@Tag(name = "Venta Management System")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    @Operation(summary = "View a list of available ventas")
    public ResponseEntity<List<Venta>> getAllVentas() {
        List<Venta> ventas = ventaService.findAll();
        if (ventas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a venta by Id")
    public ResponseEntity<Venta> getVentaById(@PathVariable Integer id) {
        Venta venta = ventaService.findById(id);
        if (venta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(venta);
    }

    @PostMapping
    @Operation(summary = "Add a new venta")
    public ResponseEntity<Venta> createVenta(@RequestBody Venta venta) {
        Venta createdVenta = ventaService.save(venta);
        return ResponseEntity.status(201).body(createdVenta);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing venta")
    public ResponseEntity<Venta> updateVenta(@PathVariable Integer id, @RequestBody Venta venta) {
        venta.setId(id);
        Venta updatedVenta = ventaService.save(venta);
        if (updatedVenta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedVenta);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partial update an existing venta")
    public ResponseEntity<Venta> updatePartialVenta(@PathVariable Integer id, @RequestBody Venta venta) {
        venta.setId(id);
        Venta updatedVenta = ventaService.partialUpdate(venta);
        if (updatedVenta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedVenta);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a venta")
    public ResponseEntity<Void> deleteVenta(@PathVariable Integer id) {
        ventaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

