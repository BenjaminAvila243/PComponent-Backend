package PComponent_Eva3.PComponent.controller;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

import PComponent_Eva3.PComponent.assembler.VentaModelAssembler;
import PComponent_Eva3.PComponent.model.Venta;
import PComponent_Eva3.PComponent.service.VentaService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
@RequestMapping("/api/v2/Ventas")
public class VentaControllerV2 {
    
    @Autowired
    private VentaService ventaService;

    @Autowired
    private VentaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Venta>> getAllVentas() {
        List<EntityModel<Venta>> Ventas = ventaService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                Ventas,
                linkTo(methodOn(VentaControllerV2.class).getAllVentas()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Venta> getVentaById(@PathVariable Long id) {
        Venta Venta = ventaService.findById(id.intValue());
        return assembler.toModel(Venta);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Venta>> createVenta(@RequestBody Venta Venta) {
        Venta newVenta = ventaService.save(Venta);
        return ResponseEntity
                .created(linkTo(methodOn(VentaControllerV2.class).getVentaById(Long.valueOf(newVenta.getId())))
                        .toUri())
                .body(assembler.toModel(newVenta));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Venta>> updateVenta(@PathVariable Long id, @RequestBody Venta Venta) {
        Venta.setId(id.intValue());
        Venta updated = ventaService.save(Venta);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(updated));
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Venta>> patchVenta(@PathVariable Long id, @RequestBody Venta Venta) {
        Venta.setId(id.intValue());
        Venta updated = ventaService.partialUpdate(Venta);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(updated));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteVenta(@PathVariable Long id) {
        ventaService.deleteById(id.intValue());
        return ResponseEntity.noContent().build();
    }
}
