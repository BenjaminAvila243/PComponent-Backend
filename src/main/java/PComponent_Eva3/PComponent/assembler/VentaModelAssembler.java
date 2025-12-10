package PComponent_Eva3.PComponent.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import PComponent_Eva3.PComponent.controller.VentaControllerV2;
import PComponent_Eva3.PComponent.model.Venta;


@Component
public class VentaModelAssembler implements RepresentationModelAssembler<Venta, EntityModel<Venta>> {


    @SuppressWarnings("null")
    @Override
    public EntityModel<Venta> toModel(Venta venta) {
        return EntityModel.of(venta,
                linkTo(methodOn(VentaControllerV2.class).getVentaById(Long.valueOf(venta.getId()))).withSelfRel(),
                linkTo(methodOn(VentaControllerV2.class).getAllVentas()).withRel("Ventas"),
                linkTo(methodOn(VentaControllerV2.class).updateVenta(Long.valueOf(venta.getId()), venta)).withRel("actualizar"),
                linkTo(methodOn(VentaControllerV2.class).deleteVenta(Long.valueOf(venta.getId()))).withRel("eliminar"),
                linkTo(methodOn(VentaControllerV2.class).patchVenta(Long.valueOf(venta.getId()), venta)).withRel("actualizar-parcial")
        );
    }
}
