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

import PComponent_Eva3.PComponent.service.CarritoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import PComponent_Eva3.PComponent.model.Carrito;


@RestController
@RequestMapping("/api/carrito")
@Tag(name = "Carrito Management System")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    @Operation(summary = "View a list of available carritos")
    public ResponseEntity<List<Carrito>> getAllCarritos() {
        List<Carrito> carritos = carritoService.findAll();
        if (carritos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carritos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a carrito by Id")
    public ResponseEntity<Carrito> getCarritoById(@PathVariable Integer id) {
        Carrito carrito = carritoService.findById(id);
        if (carrito == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carrito);
    }

    @PostMapping
    @Operation(summary = "Add a new carrito")
    public ResponseEntity<Carrito> createCarrito(@RequestBody Carrito carrito) {
        Carrito createdCarrito = carritoService.save(carrito);
        return ResponseEntity.status(201).body(createdCarrito);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing carrito")
    public ResponseEntity<Carrito> updateCarrito(@PathVariable Integer id, @RequestBody Carrito carrito) {
        carrito.setId(id);
        Carrito updatedCarrito = carritoService.save(carrito);
        if (updatedCarrito == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCarrito);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partial update an existing carrito")
    public ResponseEntity<Carrito> updatePartialCarrito(@PathVariable Integer id, @RequestBody Carrito carrito) {
        carrito.setId(id);
        Carrito updatedCarrito = carritoService.partialUpdate(carrito);
        if (updatedCarrito == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCarrito);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a carrito")
    public ResponseEntity<Void> deleteCarrito(@PathVariable Integer id) {
        carritoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

