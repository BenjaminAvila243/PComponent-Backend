package PComponent_Eva3.PComponent.assembler;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import PComponent_Eva3.PComponent.controller.ProductoControllerV2;
import PComponent_Eva3.PComponent.model.Producto;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {
    @SuppressWarnings("null")
    @Override
    public EntityModel<Producto> toModel(Producto producto) {
        return EntityModel.of(producto,
                linkTo(methodOn(ProductoControllerV2.class).getProductoById(Integer.valueOf(producto.getId()))).withSelfRel(),
                linkTo(methodOn(ProductoControllerV2.class).getAllProductos()).withRel("productos"),
                linkTo(methodOn(ProductoControllerV2.class).updateProducto(Integer.valueOf(producto.getId()), producto)).withRel("actualizar"),
                linkTo(methodOn(ProductoControllerV2.class).deleteProducto(Integer.valueOf(producto.getId()))).withRel("eliminar"),
                linkTo(methodOn(ProductoControllerV2.class).updatePartialProducto(Integer.valueOf(producto.getId()), producto)).withRel("actualizar-parcial")
        );
    }
}
