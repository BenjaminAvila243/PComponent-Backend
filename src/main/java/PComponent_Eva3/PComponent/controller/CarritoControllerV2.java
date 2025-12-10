package PComponent_Eva3.PComponent.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import PComponent_Eva3.PComponent.assembler.CarritoModelAssembler;
import PComponent_Eva3.PComponent.model.Carrito;
import PComponent_Eva3.PComponent.service.CarritoService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/v2/carrito")
public class CarritoControllerV2 {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private CarritoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Carrito>> getAllCarritos() {
        List<EntityModel<Carrito>> carritos = carritoService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                carritos,
                linkTo(methodOn(CarritoControllerV2.class).getAllCarritos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Carrito> getCarritoById(@PathVariable Long id) {
        Carrito carrito = carritoService.findById(id.intValue());
        return assembler.toModel(carrito);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Carrito>> createCarrito(@RequestBody Carrito carrito) {
        Carrito newCarrito = carritoService.save(carrito);
        return ResponseEntity
                .created(linkTo(methodOn(CarritoControllerV2.class).getCarritoById(Long.valueOf(newCarrito.getId())))
                        .toUri())
                .body(assembler.toModel(newCarrito));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Carrito>> updateCarrito(@PathVariable Long id, @RequestBody Carrito carrito) {
        carrito.setId(id.intValue());
        Carrito updated = carritoService.save(carrito);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(updated));
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Carrito>> patchCarrito(@PathVariable Long id, @RequestBody Carrito carrito) {
        carrito.setId(id.intValue());
        Carrito updated = carritoService.partialUpdate(carrito);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(updated));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteCarrito(@PathVariable Long id) {
        carritoService.deleteById(id.intValue());
        return ResponseEntity.noContent().build();
    }
}