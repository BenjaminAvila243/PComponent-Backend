package PComponent_Eva3.PComponent.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import PComponent_Eva3.PComponent.controller.MetodoEnvioControllerV2;
import PComponent_Eva3.PComponent.model.Metodoenvio;

@Component
public class MetodoEnvioModelAssembler implements RepresentationModelAssembler<Metodoenvio, EntityModel<Metodoenvio>> {
    
    @SuppressWarnings("null")
    @Override
    public EntityModel<Metodoenvio> toModel(Metodoenvio metodo) {
        return EntityModel.of(metodo,
                linkTo(methodOn(MetodoEnvioControllerV2.class).getMetodoEnvioById(Integer.valueOf(metodo.getId()))).withSelfRel(),
                linkTo(methodOn(MetodoEnvioControllerV2.class).getAllMetodosEnvio()).withRel("metodos-envio"),
                linkTo(methodOn(MetodoEnvioControllerV2.class).updateMetodoEnvio(Integer.valueOf(metodo.getId()), metodo)).withRel("actualizar"),
                linkTo(methodOn(MetodoEnvioControllerV2.class).deleteMetodoEnvio(Integer.valueOf(metodo.getId()))).withRel("eliminar"),
                linkTo(methodOn(MetodoEnvioControllerV2.class).patchMetodoEnvio(Integer.valueOf(metodo.getId()), metodo)).withRel("actualizar-parcial")
        );
    }
}