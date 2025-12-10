package PComponent_Eva3.PComponent.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import PComponent_Eva3.PComponent.controller.MarcaControllerV2;
import PComponent_Eva3.PComponent.model.Marca;

@Component
public class MarcaModelAssembler implements RepresentationModelAssembler<Marca, EntityModel<Marca>> {

    @SuppressWarnings("null")
    @Override
    public EntityModel<Marca> toModel(Marca marca) {
        return EntityModel.of(marca,
                linkTo(methodOn(MarcaControllerV2.class).getMarcaById(Integer.valueOf(marca.getId()))).withSelfRel(),
                linkTo(methodOn(MarcaControllerV2.class).getAllMarcas()).withRel("marcas"),
                linkTo(methodOn(MarcaControllerV2.class).updateMarca(Integer.valueOf(marca.getId()), marca)).withRel("actualizar"),
                linkTo(methodOn(MarcaControllerV2.class).deleteMarca(Integer.valueOf(marca.getId()))).withRel("eliminar"),
                linkTo(methodOn(MarcaControllerV2.class).updatePartialMarca(Integer.valueOf(marca.getId()), marca)).withRel("actualizar-parcial")
        );
    }
}