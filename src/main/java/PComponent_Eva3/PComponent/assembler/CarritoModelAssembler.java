package PComponent_Eva3.PComponent.assembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import PComponent_Eva3.PComponent.controller.CarritoControllerV2;
import PComponent_Eva3.PComponent.model.Carrito;

@Component
public class CarritoModelAssembler implements RepresentationModelAssembler<Carrito, EntityModel<Carrito>> {

    @SuppressWarnings("null")
    @Override
    public EntityModel<Carrito> toModel(Carrito carrito) {
        return EntityModel.of(carrito,
                linkTo(methodOn(CarritoControllerV2.class).getCarritoById(Long.valueOf(carrito.getId()))).withSelfRel(),
                linkTo(methodOn(CarritoControllerV2.class).getAllCarritos()).withRel("carritos"),
                linkTo(methodOn(CarritoControllerV2.class).updateCarrito(Long.valueOf(carrito.getId()), carrito)).withRel("actualizar"),
                linkTo(methodOn(CarritoControllerV2.class).deleteCarrito(Long.valueOf(carrito.getId()))).withRel("eliminar"),
                linkTo(methodOn(CarritoControllerV2.class).patchCarrito(Long.valueOf(carrito.getId()), carrito)).withRel("actualizar-parcial")
        );
    }
}